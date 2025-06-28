package com.example.atonce.presentation.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atonce.core.enums.ErrorMessagesEnum
import com.example.atonce.domain.Response
import com.example.atonce.data.remote.dto.AreaDto
import com.example.atonce.data.remote.dto.authentication.RegisterRequestDto
import com.example.atonce.domain.usecase.GetAreasUseCase
import com.example.atonce.domain.usecase.GetGovernoratesUseCase
import com.example.atonce.domain.usecase.RegisterUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val getGovernoratesUseCase: GetGovernoratesUseCase,
    private val getAreasUseCase: GetAreasUseCase,
    private val registerUseCase: RegisterUseCase
): ViewModel() {

    private val _registerState = MutableStateFlow<Response<Boolean>?>(null)
    val registerState = _registerState.asStateFlow()

    private val _message = MutableSharedFlow<String>()
    val message = _message.asSharedFlow()

    private val _governorates = MutableStateFlow<List<AreaDto>>(emptyList())
    val governorates = _governorates.asStateFlow()

    private val _areas = MutableStateFlow<List<AreaDto>>(emptyList())
    val areas = _areas.asStateFlow()


    fun signUp(
        username: String,
        pharmacyName: String,
        email: String,
        phone: String,
        governorate: String,
        areaId: Int,
        addressDetails: String,
        invitationCode: String,
        password: String,
        confirmPassword: String
    ){
        viewModelScope.launch(Dispatchers.IO) {
            if (username.isEmpty() || pharmacyName.isEmpty() || email.isEmpty() || phone.isEmpty() ||
                governorate.isEmpty() || addressDetails.isEmpty() || password.isEmpty() ||
                confirmPassword.isEmpty()) {
                _message.emit(ErrorMessagesEnum.EMPTYFIELDS.getLocalizedMessage())
                return@launch
            }
            if (password != confirmPassword) {
                _message.emit(ErrorMessagesEnum.CONFIRMATIONERROR.getLocalizedMessage())
                return@launch
            }
            if (invitationCode.isEmpty()) {
                _message.emit(ErrorMessagesEnum.SCANQRCODE.getLocalizedMessage())
                return@launch
            }
            else{
                val request = RegisterRequestDto(
                    userName = username,
                    name = pharmacyName,
                    email = email,
                    phoneNumber = phone,
                    governate = governorate,
                    areaId = areaId,
                    address = addressDetails,
                    representativeCode = invitationCode,
                    password = password,
                    confirmPassword = confirmPassword
                )
                _registerState.value = Response.Loading
                registerUseCase(request)
                    .catch(
                        {e->
                            _message.emit(ErrorMessagesEnum.NETWORKERROR.getLocalizedMessage())
                        }
                    )
                    .collect{
                    if (it.success){
                        _registerState.value = Response.Success(true)
                        _message.emit(ErrorMessagesEnum.REGISTERSUCCESS.getLocalizedMessage())
                    }
                    else{
                        _registerState.value = Response.Error(
                            it.message
                        )
                        _message.emit(it.message)
                    }
                }
            }
        }
    }

    @OptIn(FlowPreview::class)
    fun getGovernorates(){
        viewModelScope.launch(Dispatchers.IO) {
            getGovernoratesUseCase()
                .debounce(500)
                .collect{
                _governorates.emit(it)
            }
        }
    }

    fun getAreas(governorateId: Int){
        viewModelScope.launch(Dispatchers.IO) {

            getAreasUseCase(governorateId).collect{
                _areas.emit(it)
            }
        }
    }
}