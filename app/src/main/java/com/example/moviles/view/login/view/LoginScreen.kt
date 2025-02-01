package com.example.moviles.view.login.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.moviles.R
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val scope = rememberCoroutineScope()
    val navigateToHome by viewModel.navigateToHome.collectAsState(initial = false)

    LaunchedEffect(navigateToHome) {
        if (navigateToHome) {
            navController.navigate("home_screen")
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            LoginContent(
                modifier = Modifier.align(Alignment.Center),
                viewModel = viewModel,
                onLoginClick = { scope.launch { viewModel.onLoginButtonClicked() } }
            )
        }
    }
}

@Composable
private fun LoginContent(
    modifier: Modifier,
    viewModel: LoginViewModel,
    onLoginClick: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        HeaderImage(Modifier.padding(bottom = 32.dp))
        EmailField(viewModel)
        PasswordField(viewModel)
        LoginButton(onLoginClick)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun EmailField(viewModel: LoginViewModel) {
    val errorEmail = viewModel.errorEmail
    OutlinedTextField(
        value = viewModel.email,
        onValueChange = viewModel::onEmailChanged,
        modifier = Modifier.fillMaxWidth(),
        label = { Text("Email", color = MaterialTheme.colorScheme.onBackground) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        singleLine = true,
        isError = errorEmail != null,
        supportingText = {
            if (errorEmail != null) {
                Text(text = errorEmail, color = MaterialTheme.colorScheme.error)
            }
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.outline,
            errorBorderColor = MaterialTheme.colorScheme.error,
        ),
        shape = RoundedCornerShape(12.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PasswordField(viewModel: LoginViewModel) {
    var passwordVisible by remember { mutableStateOf(false) }
    OutlinedTextField(
        value = viewModel.password,
        onValueChange = viewModel::onPasswordChanged,
        modifier = Modifier.fillMaxWidth(),
        label = { Text("Password", color = MaterialTheme.colorScheme.onBackground) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true,
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                val icon = if (passwordVisible) R.drawable.visibility_off else R.drawable.visibility
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = if (passwordVisible) "Hide password" else "Show password"
                )
            }
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.outline,
            errorBorderColor = MaterialTheme.colorScheme.error,
        ),
        shape = RoundedCornerShape(12.dp)
    )
}

@Composable
private fun LoginButton(onLoginClick: () -> Unit) {
    Button(
        onClick = onLoginClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(
            text = "Iniciar sesión",
            color = MaterialTheme.colorScheme.onPrimary,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
    }
}

@Composable
private fun HeaderImage(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.img),
        contentDescription = "Header",
        modifier = modifier
    )
}