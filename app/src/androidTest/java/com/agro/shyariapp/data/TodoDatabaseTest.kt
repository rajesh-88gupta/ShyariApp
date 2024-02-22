package com.agro.shyariapp.data

import android.content.Context

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.UnconfinedTestDispatcher

import kotlinx.coroutines.test.runTest
import org.junit.After

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(JUnit4::class)
class TodoDatabaseTest {
    private lateinit var db: TodoDatabase
    private lateinit var dao: TodoDao

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, TodoDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        dao = db.getDao()
    }

    @After
    fun tearUp(){
        db.close()
    }
    @Test
    fun save_todo_into_table() =runTest(UnconfinedTestDispatcher()) {
        val todo = Todo("Rajesh","Engineer")
        dao.insert(todo)
        val todos =dao.getAllTodos().first()
        assertThat(todos).contains(todo)
        assertThat(todos).hasSize(1)
    }

    @Test
    fun save_task_sameTaskSaveAgain_replaceWithNewOne() = runTest(UnconfinedTestDispatcher()) {
        val todo1 = Todo("Rajesh","Engineer")
        val todo2 = Todo("Rajesh","Engineer")
        dao.insert(todo1)
        dao.insert(todo2)
        val todos =dao.getAllTodos().first()
        assertThat(todos).contains(todo2)
       // assertThat(todos).hasSize(1)
    }
}