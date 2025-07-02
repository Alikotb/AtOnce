package com.example.atonce.presentation.search.view.component

//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.atonce.R
import com.example.atonce.core.constants.AppConstants
import com.example.atonce.domain.Response
import com.example.atonce.domain.entity.Medicine
import com.example.atonce.domain.entity.SupplierEntity
import com.example.atonce.presentation.common.FontSizes.MEDICINE_BTN_SHEET_NAME
import com.example.atonce.presentation.search.viewmodel.SearchViewModel
import kotlinx.coroutines.FlowPreview
import java.util.Locale


@FlowPreview
@ExperimentalMaterial3Api
@Composable
fun ModelSheet(
    viewModel: SearchViewModel,
    snackbarHostState: SnackbarHostState,
    medicine: Medicine?,
    areaId: Int,
    onClose: () -> Unit = {}
) {
    val sheetState = rememberModalBottomSheetState()
    val config = LocalConfiguration.current
    val screenHeight = config.screenHeightDp
    val colors = MaterialTheme.colorScheme

    val uiState = viewModel.uiStateSuppliers.collectAsStateWithLifecycle()

    val medicineName : String = if (Locale.getDefault().language == "ar") medicine?.arabicMedicineName ?: "" else medicine?.medicineName ?: ""

    LaunchedEffect(Unit) {
        if (medicine != null) {
            viewModel.getAllSuppliers(areaId = areaId, medicineId = medicine.medicineId)
        }
        viewModel.message.collect { message ->
            snackbarHostState.showSnackbar(message)
        }
    }



    ModalBottomSheet(
        onDismissRequest = {
            onClose()
            viewModel.freeUiState()
        },
        sheetState = sheetState,
        containerColor = colors.surface,
        scrimColor = Color.Black.copy(alpha = 0.2f)
    ) {


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = (screenHeight * .35).dp, max = (screenHeight * .75).dp)
                .padding(bottom = 12.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                        .size(64.dp)
                        .clip(RoundedCornerShape(12.dp)),
                ) {
                    AsyncImage(
                        model = AppConstants.MEDICINE_IMAGE_URL,
                        contentDescription = "",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.FillBounds
                    )
                }
                Text(
                    text = medicineName,
                    fontSize = MEDICINE_BTN_SHEET_NAME.sp,
                    modifier = Modifier.padding(start = 12.dp, top = 8.dp),
                    fontWeight = FontWeight.Bold
                )
            }
            Divider(thickness = 2.dp, modifier = Modifier.padding(horizontal = 12.dp))

            when(uiState.value) {
                is Response.Error -> {
                    Text(text = "Error")
                }

                is Response.Loading -> {
                    LazyColumn{
                        items(3){
                            ShimmerSearchCard()
                        }
                    }
                }

                is Response.Success -> {
                    val list = (uiState.value as Response.Success<List<SupplierEntity>>).data
                    LazyColumn(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 16.dp)
                            .padding(vertical = 12.dp),
                        contentPadding = PaddingValues(
                            vertical = 12.dp
                        )
                    ) {
                        items(list) { item ->
                            BottomSheetCard(
                                supplier = item,
                                loadingItemId = viewModel.loadingItemId,
                                onAddToCartClick = {
                                    viewModel.addToCart(
                                        item.warehouseId,
                                        item.medicineId
                                    )
                                }
                            )
                            Spacer(Modifier.height(8.dp))
                        }
                    }
                }
            }
        }
    }

}