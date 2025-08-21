package com.reezvan.todoapp.presentation


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.CheckCircleOutline
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.reezvan.todoapp.model.WorkModel
import com.reezvan.todoapp.viewModel.TodoViewModel
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import java.nio.file.WatchEvent
import java.time.LocalDateTime
import kotlin.random.Random


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, todoViewModel: TodoViewModel) {
    val workList by todoViewModel.getAllWork.collectAsStateWithLifecycle(emptyList())
    var openAddAlert by remember { mutableStateOf(false) }


    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {openAddAlert=!openAddAlert}){
                Icon(imageVector = Icons.Filled.Add, contentDescription = null )
            }
        }

    )
    {innerPadding->
        val uiColor=if(isSystemInDarkTheme())Color.White else Color.Black
        var name by remember { mutableStateOf("") }
        var deadline by remember { mutableStateOf("") }
        var createdAt by remember { mutableStateOf("") }
        var priority by remember { mutableStateOf("") }
        var desc by remember { mutableStateOf("") }
        if (openAddAlert){
            AlertDialog(onDismissRequest = {openAddAlert=false}){
                Surface(
                    modifier = Modifier
                        .wrapContentWidth()
                        .wrapContentHeight(),
                    shape = MaterialTheme.shapes.large,
                    color = MaterialTheme.colorScheme.surface
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {

                        TextField(
                            value = name,
                            onValueChange = {name=it},
                            label = {Text(
                                text = "Enter work name",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color.White
                            )},
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = MaterialTheme.colorScheme.onSurface,
                                unfocusedContainerColor = MaterialTheme.colorScheme.onSurface,
                                focusedTextColor = Color.White,
                                unfocusedTextColor = Color.White
                            )
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        TextField(
                            value = priority,
                            onValueChange = {priority=it},
                            label = {Text(
                                text = "Enter priority level",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color.White
                            )},
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = MaterialTheme.colorScheme.onSurface,
                                unfocusedContainerColor = MaterialTheme.colorScheme.onSurface,
                                focusedTextColor = Color.White,
                                unfocusedTextColor = Color.White
                            )
                        )
                        Spacer(Modifier.height(8.dp))
                        TextField(
                            value = desc,
                            onValueChange = {desc=it},
                            label = {Text(
                                text = "Enter work description",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color.White
                            )},
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = MaterialTheme.colorScheme.onSurface,
                                unfocusedContainerColor = MaterialTheme.colorScheme.onSurface,
                                focusedTextColor = Color.White,
                                unfocusedTextColor = Color.White
                            )
                        )
                        Spacer(Modifier.height(8.dp))
                        TextField(
                            value = deadline,
                            onValueChange = {deadline=it},
                            label = {Text(
                                text = "Enter work deadline",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color.White
                            )},
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = MaterialTheme.colorScheme.onSurface,
                                unfocusedContainerColor = MaterialTheme.colorScheme.onSurface,
                                focusedTextColor = Color.White,
                                unfocusedTextColor = Color.White
                            )
                        )
                        Spacer(Modifier.height(16.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            TextButton(onClick = {
                                openAddAlert=false
                                createdAt= LocalDateTime.now().toString()
                                todoViewModel.addWork(
                                    workName = name,
                                    deadline = deadline.ifBlank { "Empty" },
                                    priority = priority,
                                    description = desc.ifBlank {"Empty" },
                                    createdTime = createdAt
                                )
                                name=""
                                desc=""
                                deadline=""
                                priority=""
                                createdAt=""
                            }) {

                                Text(text="Confirm", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                            }

                            TextButton(
                                onClick = {
                                    openAddAlert = false
                                    name=""
                                    desc=""
                                    deadline=""
                                    priority=""
                                    createdAt=""

                                },
                                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary)
                            ) {
                                Text(text="Cancel", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                            }

                        }

                    }

                }

            }
        }
        if (workList.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    "No tasks yet. Add one using the Button!",
                    modifier = Modifier.padding(16.dp),
                    color = uiColor
                )
            }
        }else {
            LazyColumn(modifier = Modifier.fillMaxSize().padding(innerPadding).padding(8.dp)) {
                items(workList.size) { work ->
                    EachTodoLook(
                        workList.elementAt(work),
                        onDelete = { todoViewModel.deleteWork(work = workList.elementAt(work)) }
                    ){

                    }
                    Spacer(Modifier.height(8.dp))
                }
            }
        }
    }


}

@Composable
fun EachTodoLook(model: WorkModel,onDelete:()-> Unit,onEdit:()-> Unit) {
    val completed by remember { mutableStateOf(false) }

    val uiColor=if(isSystemInDarkTheme()) Color.White else Color.Black
    Column(modifier = Modifier.fillMaxWidth().height(122.dp)
        .background(color = MaterialTheme.colorScheme.onSurface, shape = RoundedCornerShape(8.dp)))
    {
            Row(modifier = Modifier.fillMaxSize().fillMaxHeight()) {

                Column(modifier = Modifier.fillMaxHeight()) {
                    IconButton(onClick ={onEdit}, modifier = Modifier.size(40.dp).padding(start = 2.dp) ) {
                        Icon(
                            imageVector = Icons.Filled.EditNote,
                            contentDescription = null,
                            tint=uiColor
                        )
                    }
                    Spacer(modifier = Modifier.height(2.dp))
                    IconButton(onClick = {completed !=completed}, modifier = Modifier.size(40.dp)) {
                        Icon(
                            imageVector =  if(completed)Icons.Filled.CheckCircle else Icons.Filled.CheckCircleOutline ,
                            contentDescription = null,
                            tint=uiColor
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    IconButton(onClick = {onDelete},modifier = Modifier.padding(bottom = 4.dp).size(40.dp)) {
                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = "",
                            tint=uiColor,

                        )

                    }

                }
                Spacer(modifier = Modifier.width(8.dp))
                if (completed){
                    HorizontalDivider(
                        modifier = Modifier.fillMaxWidth().height(2.dp),
                        thickness = DividerDefaults.Thickness,
                        color = Color.Gray
                    )
                }

                Column(modifier = Modifier.fillMaxWidth(0.5f).fillMaxHeight()) {
                    Text(
                        text = model.workName,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = uiColor,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Priority:${model.priority}",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily.Monospace,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = uiColor
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = model.description.ifBlank { "Empty" },
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = FontFamily.Monospace,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        color = uiColor
                    )



                }
                Spacer(modifier = Modifier.width(16.dp))
                Column(modifier = Modifier.fillMaxWidth(0.8f).fillMaxHeight()) {
                    Text(
                        text = "Created At:",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = uiColor,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text=model.createdTime,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily.Monospace,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = uiColor

                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Deadline:",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = uiColor
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = model.deadline.ifBlank { "Empty" },
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily.Monospace,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = uiColor
                    )

                }
            }
        }

    }

