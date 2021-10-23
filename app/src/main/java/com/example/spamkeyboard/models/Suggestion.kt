package com.example.spamkeyboard.models

class Suggestion private constructor(builder: Suggestion.Builder) {

    val id: String?
    val sugg: String?

    init {
        this.id = builder.id
        this.sugg = builder.sugg
    }

    class Builder {
        var sugg: String? = null

        // builder code
        var id: String? = null
    }
}