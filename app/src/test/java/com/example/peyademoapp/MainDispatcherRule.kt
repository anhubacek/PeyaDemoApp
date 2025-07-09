package com.example.peyademoapp

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import org.junit.rules.TestWatcher

@ExperimentalCoroutinesApi
class MainDispatcherRule(

    val testDispatcher: TestDispatcher = StandardTestDispatcher()
) : TestWatcher() {


}