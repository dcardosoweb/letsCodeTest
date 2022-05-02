package com.example.letscodetest.utils

import java.io.IOException

class NoConnectivityException : IOException() {

    override val message: String?
        get() = "Sem internet. Verifique a sua conexão."
}