package com.example.letscodetest.models

class UserModel() {
    constructor(name: String, email: String, password:String) : this() {
        this.name = name
        this.email = email
        this.password = password
    }
    lateinit var id:String
    lateinit var name:String
    lateinit var email:String
    lateinit var password:String
}