package com.frankitoo.data.mapping

import com.frankitoo.data.models.CharacterDto
import com.frankitoo.domain.models.character.Character
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.tasks.await

suspend fun CharacterDto.toDomain(storageRef: StorageReference): Character {
    val imageUrl = imageUrl?.let {
        storageRef.child(it).downloadUrl.await().toString()
    }
    return Character(
        id = id ?: 0,
        name = name,
        attack = attack,
        defense = defense,
        speed = speed,
        special = special,
        skills = skills,
        imageUrl = imageUrl
    )
}
