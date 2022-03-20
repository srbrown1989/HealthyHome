package com.example.android.healthyhome.database.mail

import org.jetbrains.anko.doAsync
import java.util.*
import javax.mail.*
import javax.mail.internet.*

class Mail {
    companion object {

        val email = "healthyhomenoreply@gmail.com"
        val password = "healthyhome0320"

        fun sendEMail(subject: String, message: String, recipient: String){
            doAsync {
                subSend(arrayOf(subject, message, recipient))
            }
        }

        private fun subSend(args: Array<String>) {
            val userName =  email
            val password =  password
            // FYI: passwords as a command arguments isn't safe
            // They go into your bash/zsh history and are visible when running ps

            val emailFrom = email
            val emailTo = args[2]
            val emailCC = ""

            val subject = args[0]
            val text = args[1]

            val props = Properties()
            putIfMissing(props, "mail.smtp.host", "smtp.gmail.com")
            putIfMissing(props, "mail.smtp.port", "587")
            putIfMissing(props, "mail.smtp.auth", "true")
            putIfMissing(props, "mail.smtp.starttls.enable", "true")

            val session = Session.getDefaultInstance(props, object : javax.mail.Authenticator() {
                override fun getPasswordAuthentication(): PasswordAuthentication {
                    return PasswordAuthentication(userName, password)
                }
            })

            session.debug = true

            try {
                val mimeMessage = MimeMessage(session)
                mimeMessage.setFrom(InternetAddress(emailFrom))
                mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo, false))
                mimeMessage.setRecipients(Message.RecipientType.CC, InternetAddress.parse(emailCC, false))
                mimeMessage.setText(text)
                mimeMessage.subject = subject
                mimeMessage.sentDate = Date()

                val smtpTransport = session.getTransport("smtp")
                smtpTransport.connect()
                smtpTransport.sendMessage(mimeMessage, mimeMessage.allRecipients)
                smtpTransport.close()
            } catch (messagingException: MessagingException) {
                messagingException.printStackTrace()
            }
        }

        private fun putIfMissing(props: Properties, key: String, value: String) {
            if (!props.containsKey(key)) {
                props[key] = value
            }
        }

    }
}