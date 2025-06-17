package com.example.atonce.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.atonce.R
import com.example.atonce.presentation.home.component.AdPager
import com.example.atonce.presentation.home.component.WarehouseCard
import com.example.atonce.presentation.theme.SemiBoldFont

@Composable
fun HomeScreen() {
    val warehouses = listOf(
        Warehouse("Hamada Pharmacy", "Zefta, Gharbia", 700, 4),
        Warehouse("Hamada Pharmacy", "Cairo Downtown", 650, 3),
        Warehouse("Hamada Pharmacy", "Alexandria", 720, 5),
        Warehouse("Hamada Pharmacy", "Zefta, Gharbia", 700, 4),
        Warehouse("Hamada Pharmacy", "Cairo Downtown", 650, 3),
        Warehouse("Hamada Pharmacy", "Alexandria", 720, 5)
    )

    val ads = listOf(
        R.drawable.ads,
        R.drawable.ads1,
        R.drawable.ads2
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF9F9F9))
            .padding(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(bottom = 32.dp)
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CircularIconButton(
                    icon = Icons.Default.Call,
                    onClick = { },
                    tint = Color(0xFF1A998E)
                )

                Text(
                    text = "Home Screen",
                    fontFamily = SemiBoldFont,
                    fontSize = 22.sp,
                    color = Color(0xFF333333)
                )

                CircularIconButton(
                    icon = Icons.Default.Person,
                    onClick = { },
                    tint = Color(0xFF1A998E)
                )
            }
        }

        item {
            AdPager(
                ads = ads,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .shadow(8.dp, RoundedCornerShape(12.dp))
            )
        }

        item {
            Text(
                text = "Warehouses Nearby",
                fontFamily = SemiBoldFont,
                fontSize = 20.sp,
                color = Color(0xFF1A998E),
                modifier = Modifier.padding(16.dp)
            )
        }

        items(warehouses) { warehouse ->
            WarehouseCard(warehouse = warehouse)
        }
    }
}


@Composable
fun CircularIconButton(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit,
    tint: Color
) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(Color.White)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = tint,
            modifier = Modifier.size(24.dp)
        )
    }
}



data class Warehouse(
    val name: String,
    val location: String,
    val minOrder: Int,
    val rating: Int,
    val imageRes: Int = R.drawable.pharmacy
)