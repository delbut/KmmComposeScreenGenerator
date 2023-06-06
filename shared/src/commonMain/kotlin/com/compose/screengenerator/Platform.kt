package com.compose.screengenerator

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform