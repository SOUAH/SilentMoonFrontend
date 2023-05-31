package com.nistruct.meditation.data.repo

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.nistruct.meditation.data.AppDataStore.DataStoreInterface
import com.nistruct.meditation.data.entity.UserResponse
import com.nistruct.meditation.utils.Constants.ACCESS_TOKEN
import com.nistruct.meditation.utils.Constants.DATASTORE_NAME
import com.nistruct.meditation.utils.Constants.USER_EMAIL
import com.nistruct.meditation.utils.Constants.USER_FAVORITE_TOPIC
import com.nistruct.meditation.utils.Constants.USER_ID
import com.nistruct.meditation.utils.Constants.USER_NAME
import com.nistruct.meditation.utils.Constants.USER_NOTIFICATION_DAYS
import com.nistruct.meditation.utils.Constants.USER_NOTIFICATION_TIME
import kotlinx.coroutines.flow.first
import javax.inject.Inject

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATASTORE_NAME)

class DataStoreRepository @Inject constructor(private val context: Context) : DataStoreInterface {

    override suspend fun putAccessToken(value: String) {
        val preferencesKey = stringPreferencesKey(ACCESS_TOKEN)

        context.dataStore.edit { preferences ->
            preferences[preferencesKey] = value
        }
    }

    override suspend fun getAccessToken(): String? {
        val preferencesKey = stringPreferencesKey(ACCESS_TOKEN)
        val preference = context.dataStore.data.first()

        return preference[preferencesKey].toString()
    }

    override suspend fun deleteAccessToken() {
        val preferencesKey = stringPreferencesKey(ACCESS_TOKEN)

        context.dataStore.edit { preferences ->
            preferences.remove(preferencesKey)
        }
    }

    override suspend fun putUserResponse(userResponse: UserResponse) {
        context.dataStore.edit { preferences ->
            preferences[stringPreferencesKey(USER_ID)] = userResponse.id
            preferences[stringPreferencesKey(USER_NAME)] = userResponse.userName
            preferences[stringPreferencesKey(USER_EMAIL)] = userResponse.email
            preferences[stringPreferencesKey(USER_NOTIFICATION_TIME)] =
                userResponse.notificationTime
            preferences[stringPreferencesKey(USER_FAVORITE_TOPIC)] = userResponse.favoriteTopic

            // Convert Array to String using joinToString function with a separator
            preferences[stringPreferencesKey(USER_NOTIFICATION_DAYS)] =
                userResponse.notificationDays.joinToString(separator = ",")
        }
    }

    override suspend fun getUserResponse(): UserResponse? {
        val preference = context.dataStore.data.first()

        val id = preference[stringPreferencesKey(USER_ID)] ?: return null
        val userName = preference[stringPreferencesKey(USER_NAME)] ?: return null
        val email = preference[stringPreferencesKey(USER_EMAIL)] ?: return null
        val notificationTime =
            preference[stringPreferencesKey(USER_NOTIFICATION_TIME)] ?: return null
        val favoriteTopic = preference[stringPreferencesKey(USER_FAVORITE_TOPIC)] ?: return null

        // Convert String to Array using split function
        val notificationDaysString =
            preference[stringPreferencesKey(USER_NOTIFICATION_DAYS)] ?: return null
        val notificationDays = notificationDaysString.split(",").toTypedArray()

        return UserResponse(id, userName, email, notificationDays, notificationTime, favoriteTopic)
    }

    override suspend fun getUserId(): String? {
        val preference = context.dataStore.data.first()
        return preference[stringPreferencesKey(USER_ID)]
    }

    override suspend fun getUserName(): String? {
        val preference = context.dataStore.data.first()
        return preference[stringPreferencesKey(USER_NAME)]
    }

    override suspend fun getUserEmail(): String? {
        val preference = context.dataStore.data.first()
        return preference[stringPreferencesKey(USER_EMAIL)]
    }

    override suspend fun getUserNotificationTime(): String? {
        val preference = context.dataStore.data.first()
        return preference[stringPreferencesKey(USER_NOTIFICATION_TIME)]
    }

    override suspend fun getUserFavoriteTopic(): String? {
        val preference = context.dataStore.data.first()
        return preference[stringPreferencesKey(USER_FAVORITE_TOPIC)]
    }

    override suspend fun getUserNotificationDays(): Array<String>? {
        val preference = context.dataStore.data.first()
        val notificationDaysString =
            preference[stringPreferencesKey(USER_NOTIFICATION_DAYS)] ?: return null
        return notificationDaysString.split(",").toTypedArray()
    }
}