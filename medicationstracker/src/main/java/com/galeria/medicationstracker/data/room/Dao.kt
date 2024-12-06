package com.galeria.medicationstracker.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

  @Query("SELECT * FROM user")
  fun getAllUsers(): List<User>

  @Query(
    "SELECT * FROM user WHERE first_name LIKE :first AND " +
        "last_name LIKE :last LIMIT 1"
  )
  fun findByName(first: String, last: String): User

  @Insert
  fun insertAllUsers(vararg users: User)

  @Delete
  fun deleteUser(user: User)
}