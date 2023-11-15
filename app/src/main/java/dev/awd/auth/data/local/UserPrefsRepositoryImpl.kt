package dev.awd.auth.data.local

import android.content.Context
import android.util.Log
import androidx.datastore.dataStore
import dev.awd.auth.domain.models.User
import dev.awd.auth.domain.repository.UserPrefsRepository

val Context.userDataStore by dataStore("user_data.json", UserDataSerializer)

class UserPrefsRepositoryImpl(
    private val context: Context
) : UserPrefsRepository {

    override suspend fun setUserData(user: User) {
        context.userDataStore.updateData {
            it.copy(
                username = user.username,
                email = user.email,
                displayName = user.displayName,
                gender = user.gender,
                avatarUrl = user.avatarUrl
            )
        }
        Log.i("Prefs Repo", "setUserData: Success for $user ")
    }

    override fun getUserData() =
        context.userDataStore.data

    override suspend fun clearUserData() {
        context.userDataStore.updateData {
            /**Clearing only username so when we can depend on it to
             *  check for auto login in [dev.awd.auth.presentation.login.LoginViewModel]**/
            it.copy(username = "")
        }
    }
}