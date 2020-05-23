package ml.bimdev.baseapp.extensions

import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine


suspend fun FusedLocationProviderClient.getLastLocationSuspended(): Location? =
    suspendCoroutine { cont ->
        this.lastLocation
            .addOnSuccessListener { location: Location? ->
                cont.resume(location)
            }
            .addOnFailureListener {
                cont.resumeWithException(it)
            }
            .addOnCanceledListener {
                cont.resume(null)
            }
    }
