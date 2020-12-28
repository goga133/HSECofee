package com.goga133.hsecoffee.objects

import java.util.*
import javax.persistence.*
import kotlin.random.Random

/**
 * Email-код для подтверждения аккаунта.
 */
@Entity
data class ConfirmationCode(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "id")
        val id: Long = 0,

        @Column(name = "code")
        val code: Int = Random.nextInt(MIN_CODE, MAX_CODE),

        @Temporal(TemporalType.TIMESTAMP)
        val createdDate: Date = Date(),

        @OneToOne(targetEntity = User::class, fetch = FetchType.EAGER)
        @JoinColumn(nullable = false, name = "user_id")
        val user: User? = null) {

    companion object {
        private const val MIN_CODE = 100000
        private const val MAX_CODE = 1000000
    }

    constructor(user: User) : this(
            user = user,
            createdDate = Date(),
            code = Random.nextInt(MIN_CODE, MAX_CODE)) {
        //
    }
}
