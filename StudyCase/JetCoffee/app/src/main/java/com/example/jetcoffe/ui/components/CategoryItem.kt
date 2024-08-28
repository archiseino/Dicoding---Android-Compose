package com.example.jetcoffe.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetcoffe.R
import com.example.jetcoffe.model.Category
import com.example.jetcoffe.ui.theme.JetCoffeeTheme

@Composable
fun CategoryItem(
    category: Category,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = category.imageCategory),
            contentDescription = stringResource(
                id = category.textCategory
            ),
            modifier = modifier
                .size(60.dp)
                .clip(CircleShape),
        )
        Text(
            text = stringResource(id = category.textCategory),
            fontSize = 10.sp,
            modifier = modifier.paddingFromBaseline(top = 16.dp, bottom = 8.dp),
        )

    }
}

@Composable
@Preview(showBackground = true)
fun CategoryItemPreview() {
    JetCoffeeTheme {
        CategoryItem(
            category = Category(
                R.drawable.icon_category_cappuccino,
                R.string.category_cappuccino,
            ),
            modifier = Modifier.padding(horizontal = 8.dp)
        )
    }
}