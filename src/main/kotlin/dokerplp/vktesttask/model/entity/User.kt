package dokerplp.vktesttask.model.entity

class User(var login: String, var password: CharArray, val friends: List<User>)
