package com.kkalfas.shortly.history.data.source

import com.kkalfas.shortly.data.history.database.model.LinkHistoryEntity
import com.kkalfas.shortly.history.data.FunctionalityNotAvailable
import com.kkalfas.shortly.history.domain.HistoryDatabaseAdapter
import com.kkalfas.shortly.mocks.MockkTest
import io.mockk.coVerify
import io.mockk.every
import io.mockk.slot
import io.mockk.verify
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class LocalHistoryDataSourceTest : MockkTest() {

    private val databaseAdapter: HistoryDatabaseAdapter = mockk()
    private val subject = LocalHistoryDataSource(databaseAdapter)

    @Test(expected = FunctionalityNotAvailable::class)
    fun `when shortenUrl called then FunctionalityNotAvailable exception thrown`() {
        // when
        runBlocking { subject.shortenUrl("") }
    }

    @Test
    fun `when saveLink then database adapter saveLink called with correct param`() {
        // given
        val slotLink = slot<LinkHistoryEntity>()
        val link = LinkHistoryEntity(
            code = "aaaa",
            original = "bbbb",
            shorted = "b/a"
        )

        // when
        runBlocking { subject.saveLink(link) }

        // then
        coVerify {
            databaseAdapter.saveLink(capture(slotLink))
        }
        assertThat(slotLink.captured).isEqualTo(link)
    }

    @Test
    fun `when getLinkHistory then database adapter getLinkHistoryStream is called `() {
        // given
        val link0 = LinkHistoryEntity(
            code = "0a",
            original = "0b",
            shorted = "0b/0a"
        )
        val link1 = LinkHistoryEntity(
            code = "1a",
            original = "1b",
            shorted = "1b/1a"
        )
        every { databaseAdapter.getLinkHistoryStream() } returns flowOf(listOf(link0, link1))

        // when
        val flow = subject.getLinkHistory()

        // then
        runBlocking {
            flow.collect {
                assertThat(it.size).isEqualTo(2)
                assertThat(it[0].code).isEqualTo(link0.code)
                assertThat(it[0].original).isEqualTo(link0.original)
                assertThat(it[0].short).isEqualTo(link0.shorted)
                assertThat(it[1].code).isEqualTo(link1.code)
                assertThat(it[1].original).isEqualTo(link1.original)
                assertThat(it[1].short).isEqualTo(link1.shorted)
            }
        }
        verify {
            databaseAdapter.getLinkHistoryStream()
        }
    }

    @Test
    fun `when deleteLink then database adapter deleteLink is called`() {
        // given
        val slotCode = slot<String>()
        val code = "aaa"

        // when
        runBlocking { subject.deleteLink(code) }

        // then
        coVerify { databaseAdapter.deleteLink(capture(slotCode)) }
        assertThat(slotCode.captured).isEqualTo(code)
    }
}
