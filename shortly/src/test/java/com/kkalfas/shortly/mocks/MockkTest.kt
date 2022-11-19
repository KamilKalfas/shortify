package com.kkalfas.shortly.mocks

import android.util.Log
import androidx.annotation.CallSuper
import io.mockk.MockK
import io.mockk.MockKDsl
import io.mockk.clearMocks
import io.mockk.every
import io.mockk.mockkStatic
import org.junit.After
import org.junit.Before
import kotlin.reflect.KClass

abstract class MockkTest {
    val mockList = ArrayList<Any>()

    @After
    @CallSuper
    fun resetMocks() {
        mockList.forEach {
            clearMocks(it)
        }
    }

    @Before
    open fun setup() {
        mockkStatic(Log::class)
        every { Log.v(any(), any()) } returns 0
        every { Log.wtf(any(), any<Throwable>()) } returns 0
        every { Log.wtf(any(), any<String>()) } returns 0
        every { Log.d(any(), any()) } returns 0
        every { Log.i(any(), any()) } returns 0
        every { Log.e(any(), any()) } returns 0
        every { Log.println(any(), any(), any()) } returns 0
    }

    inline fun <reified T : Any> mockk(
        name: String? = null,
        relaxed: Boolean = true,
        vararg moreInterfaces: KClass<*>,
        relaxUnitFun: Boolean = true,
        instance: Boolean = false,
        block: T.() -> Unit = {}
    ): T {
        val mock = MockK.useImpl {
            MockKDsl.internalMockk(
                name,
                relaxed,
                *moreInterfaces,
                relaxUnitFun = relaxUnitFun,
                block = block
            )
        }
        if (!instance) mockList.add(mock)
        return mock
    }
}
