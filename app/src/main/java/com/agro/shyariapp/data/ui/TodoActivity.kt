package com.agro.shyariapp.data.ui


import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.agro.shyariapp.data.Todo
import com.agro.shyariapp.ui.theme.ShyariAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TodoActivity :ComponentActivity() {
    private val todoViewModel:TodoViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShyariAppTheme {
               Surface (color = MaterialTheme.colorScheme.background){
                   AddToolbar()
               }
            }
        }
    }


    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun AddToolbar(){
        Scaffold(topBar = {
            TopAppBar(title = {  Text(text = "ToDo App",
                color = Color.White,
                modifier = Modifier.fillMaxWidth())},
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Black))
        },
            floatingActionButton = {
                val openDialog = remember { mutableStateOf(false)}
                FloatingActionButton(onClick = {
                    openDialog.value = true
                }) {
                    AddDialogBox(openDialog = openDialog)
                    Icon(Icons.Default.Add, contentDescription = "Add")
                }
            }) {
            Recyclerview(todoViewModel = todoViewModel)
        }
    }
    @Composable
    fun AddDialogBox(openDialog: MutableState<Boolean>) {
        val title = remember { mutableStateOf("") }
        val description = remember { mutableStateOf("") }
        if (openDialog.value) {
            if (openDialog.value) {
                AlertDialog(
                    onDismissRequest = {
                        openDialog.value = false
                    },
                    title = {
                        Text(text = "ToDo")
                    },
                    text = {
                        Column(
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth()
                        ) {
                            OutlinedTextField(
                                value = title.value,
                                onValueChange = {
                                    title.value = it
                                },
                                placeholder = {
                                    Text(text = "Enter title")
                                },
                                label = {
                                    Text(text = "Enter title")
                                }, modifier = Modifier.padding(10.dp)
                            )
                            OutlinedTextField(
                                value = description.value,
                                onValueChange = {
                                    description.value = it
                                },
                                placeholder = {
                                    Text(text = "Enter description")
                                },
                                label = {
                                    Text(text = "Enter description")
                                }, modifier = Modifier.padding(10.dp)
                            )
                        }
                    },
                    confirmButton = {
                        OutlinedButton(onClick = {
                            insert(title,description)
                            openDialog.value = false
                        }) {
                            Text(text = "Save")
                        }
                    }
                )
            }
        }
    }
    private fun insert(title:MutableState<String>,description:MutableState<String>){
        lifecycleScope.launchWhenStarted {
            if(!TextUtils.isEmpty(title.value) && !TextUtils.isEmpty(description.value)){
                todoViewModel.insert(
                    Todo(
                        title.value,
                        description.value
                    )
                )
                Toast.makeText(this@TodoActivity,"Inserted..",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this@TodoActivity,"Empty..",Toast.LENGTH_SHORT).show()
            }
        }
    }
    @Composable
    fun EachRow(todo: Todo){

        Card(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 8.dp)
                .fillMaxWidth(),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 2.dp
            ),
            shape = RoundedCornerShape(4.dp)
        ) {
            Column(
                modifier = Modifier.padding(10.dp)
            ) {
                Text(text = todo.title,fontWeight = FontWeight.ExtraBold)
                Text(text = todo.description,fontStyle = FontStyle.Italic)
            }
        }
    }

    @Composable
    fun Recyclerview(todoViewModel: TodoViewModel){
        LazyColumn(modifier = Modifier.padding(top = 60.dp)){
            items(todoViewModel.response.value){todo->
                EachRow(todo = todo )
            }
        }
    }
}