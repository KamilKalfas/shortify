package com.kkalfas.shortly.history.data.database

import com.kkalfas.shortly.data.history.database.model.LinkHistoryEntity
import com.kkalfas.shortly.mocks.MockkTest
import io.mockk.coVerify
import io.mockk.slot
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class HistoryRoomDbAdapterTest : MockkTest() {

    private val historyDao : LinkHistoryDao = mockk()
    private val subject = HistoryRoomDbAdapter(historyDao)

    @Test
    fun `when getLinkHistoryStream then dao observeHistory method called`() {
        // when
        subject.getLinkHistoryStream()

        // then
        verify {
            historyDao.observeHistory()
        }
    }

    @Test
    fun `when saveLink then dao insertLink method called with correct value`() {
        // given
        val slotEntity = slot<LinkHistoryEntity>()
        val entity = LinkHistoryEntity(
            code = "aaaa",
            original = "bbbb",
            shorted = "b/a"
        )
        // when
        runBlocking { subject.saveLink(entity) }

        // then
        coVerify {
            historyDao.insertLink(capture(slotEntity))
        }
        assertThat(entity).isEqualTo(slotEntity.captured)
    }

    @Test
    fun `when deleteLink then dao deleteLinkById method called`() {
        // given
        val slotCode = slot<String>()
        val code = "aaa"

        // when
        runBlocking { subject.deleteLink(code) }

        // then
        coVerify {
            historyDao.deleteLinkById(capture(slotCode))
        }
        assertThat(slotCode.captured).isEqualTo(code)
    }
}
