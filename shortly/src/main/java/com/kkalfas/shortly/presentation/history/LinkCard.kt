package com.kkalfas.shortly.presentation.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kkalfas.shortly.R
import com.kkalfas.shortly.copyTextToClipboard
import com.kkalfas.shortly.data.history.model.entities.LinkEntity
import com.kkalfas.shortly.presentation.components.CopyButton
import com.kkalfas.shortly.presentation.components.text.LinkCardText
import com.kkalfas.shortly.presentation.components.text.ShortLinkCardText
import com.kkalfas.shortly.presentation.theme.ShortlyTheme
import com.kkalfas.shortly.presentation.theme.divider

@Preview
@Composable
private fun PreviewEmptyHistoryContent() {
    ShortlyTheme {
        LinkCard(
            linkEntity = LinkEntity(
                original = "http://somewhere.on/the/intra/webz",
                short = "http://its.so/sh0rt"
            )
        )
    }
}

@Composable
fun LinkCard(
    modifier: Modifier = Modifier,
    linkEntity: LinkEntity
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colors.background)
            .padding(vertical = 23.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 23.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            LinkCardText(
                modifier = Modifier.fillMaxWidth(.90f),
                text = linkEntity.original
            )
            Icon(
                modifier = Modifier.heightIn(max = 22.dp),
                painter = painterResource(id = R.drawable.ic_del),
                contentDescription = "delete"
            )
        }
        Divider(
            modifier = Modifier.padding(vertical = 12.dp),
            color = MaterialTheme.colors.divider,
            thickness = 1.dp
        )
        ShortLinkCardText(
            modifier = Modifier.padding(horizontal = 23.dp),
            text = linkEntity.short
        )
        Spacer(modifier = Modifier.height(23.dp))
        val context = LocalContext.current
        CopyButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 23.dp),
            onClick = {
                context.copyTextToClipboard(linkEntity.short)
            }
        )
    }
}
