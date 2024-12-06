package com.galeria.medicationstracker.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
  @PrimaryKey val uid: Int,
  @ColumnInfo(name = "first_name") val firstName: String?,
  @ColumnInfo(name = "last_name") val lastName: String?,
  @ColumnInfo(name = "email") val email: String?,
  @ColumnInfo(name = "password") val password: String?
)

@Entity
data class Medication(
  @PrimaryKey val medId: Int,
  @ColumnInfo(name = "name") val name: String?,
  @ColumnInfo(name = "type") val type: String?,
  @ColumnInfo(name = "form") val form: String?,
  @ColumnInfo(name = "strength") val strength: Float,
  @ColumnInfo(name = "unit") val unit: String?,
)

/*
@Entity
data class*/
