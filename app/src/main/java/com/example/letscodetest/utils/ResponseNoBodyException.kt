package com.example.letscodetest.utils

class ResponseNoBodyException : Exception() {
    override val message: String
        get() = "response no body"
}