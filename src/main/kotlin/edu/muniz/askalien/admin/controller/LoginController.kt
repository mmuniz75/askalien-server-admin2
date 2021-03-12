package edu.muniz.askalien.admin.controller

import edu.muniz.askalien.admin.domain.Question
import edu.muniz.askalien.admin.dto.UserDTO
import org.springframework.web.bind.annotation.*

@RestController
class LoginController {


    @GetMapping("/wakeup")
    fun wake() : Question {
        return Question()
    }

    @PostMapping("/login")
        fun login(@RequestBody user: UserDTO): UserDTO {
            val USER_GUEST = System.getenv("USER_GUEST")
            val USER_GUEST_PASSWORD = System.getenv("USER_GUEST_PASSWORD")
            val USER_ADMIN = System.getenv("USER_ADMIN")
            val USER_ADMIN_PASSWORD = System.getenv("USER_ADMIN_PASSWORD")

        user.apply {
            if (login == USER_ADMIN && password == USER_ADMIN_PASSWORD) role = "ADMIN"
            if (login == USER_GUEST && password == USER_GUEST_PASSWORD) role = "USER"
        }

        return user
    }

}