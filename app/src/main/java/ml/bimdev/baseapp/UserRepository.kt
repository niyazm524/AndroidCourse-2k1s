package ml.bimdev.baseapp

import kotlinx.coroutines.delay

object UserRepository {
    private val users = mutableMapOf(
        "admin" to "5051",
        "quest" to "123"
    )

    suspend fun validate(login: String, password: String): ValidationStatus {
        delay(400)
        if (!userExists(login)) return ValidationStatus.WRONG_LOGIN
        delay(400)
        if (users[login] != password) return ValidationStatus.WRONG_PASS
        delay(200)
        return ValidationStatus.SUCCESS
    }

    fun userExists(username: String) = users.containsKey(username)

    fun updatePassword(username: String, password: String) {
        if (userExists(username))
            users[username] = password
    }

    fun validateSync(login: String, password: String): Boolean {
        return userExists(login) && users[login] == password
    }
}

enum class ValidationStatus {
    WRONG_LOGIN, WRONG_PASS, SUCCESS
}
