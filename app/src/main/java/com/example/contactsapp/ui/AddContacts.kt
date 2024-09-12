package com.example.contactsapp.ui

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationRequest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.os.Looper
import android.provider.MediaStore
import android.view.Gravity
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.content.FileProvider
import com.example.contactsapp.R
import com.example.contactsapp.databinding.ActivityAddContactsBinding
import com.example.contactsapp.model.ContactData
import com.example.contactsapp.viewModel.ContactsViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import java.io.File

class AddContacts : AppCompatActivity() {
    lateinit var binding: ActivityAddContactsBinding
    private val contactsViewModel: ContactsViewModel by viewModels()
    private lateinit var photoFile: File
    private lateinit var fusedLocationClient : FusedLocationProviderClient
    lateinit var mylocation: Location

    private val cameraLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result->
        if (result.resultCode== Activity.RESULT_OK) {
            Picasso
                .get()
                .load(
                    File(photoFile.absolutePath))
                .resize(120,120)
                .centerCrop(Gravity.CENTER)
                .placeholder(R.drawable.avatar)
                .error(R.drawable.avatar)
                .transform(CropCircleTransformation())
                .into(binding.ivAvatar)
        }
    }
    val locationRequest = registerForActivityResult(ActivityResultContracts.RequestPermission()) { result ->
        if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            getLocationUpdates()
        } else {
            Toast.makeText(this, "Please grant location permission", Toast.LENGTH_LONG).show()

        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddContactsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fusedLocationClient= LocationServices.getFusedLocationProviderClient(this)
    }

    override fun onResume() {
        super.onResume()
        setContentView(binding.root)
        binding.btnAdd.setOnClickListener {
            validateAddContact()
            clearErrors()
        }
        binding.ivAvatar.setOnClickListener {
            capturePhoto()
        }
            getLocationUpdates()
        }

        fun getLocationUpdates() {
            if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                val locationRequest = com.google.android.gms.location.LocationRequest.Builder(10000)
                    .setPriority(Priority.PRIORITY_HIGH_ACCURACY)
                    .build()

                val locationCallBack = object : LocationCallback() {
                    override fun onLocationResult(p0: LocationResult) {
                        super.onLocationResult(p0)

                        for (location in p0.locations) {
                            Toast.makeText(
                                baseContext,
                                "Lat: ${location.longitude}, Long:${location.longitude}",
                                Toast.LENGTH_LONG
                            ).show()

                        }
                    }

                }
                fusedLocationClient.requestLocationUpdates(
                    locationRequest,
                    locationCallBack,
                    Looper.getMainLooper()
                )

//            getCurrentLocation(Priority.PRIORITY_BALANCED_POWER_ACCURACY,
//                CancellationTokenSource().token
//            )
//             .addOnSuccessListener { location ->
//                mylocation = location
//                Toast.
//                makeText(
//                    this, "Lat:${location.latitude},Long:${location.longitude}",
//                    Toast.LENGTH_LONG
//                ).show()
//            }
            } else {
                locationRequest.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
            }
        }

        fun capturePhoto() {
            val photoIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            photoFile = getPhotoFile("photo_${System.currentTimeMillis()}")
            val fileUri =
                FileProvider.getUriForFile(this, "com.example.contactsapp.provider", photoFile)
            photoIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri)
            cameraLauncher.launch(photoIntent)
        }

        fun getPhotoFile(fileName: String): File {
            val folder = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            val tempFile = File.createTempFile(fileName, ".jpeg", folder)

            return tempFile
        }

        fun validateAddContact() {
            val name = binding.etName.text.toString()
            val phoneNumber = binding.etPhoneNumber.text.toString()
            val email = binding.etEmail.text.toString()

            var error = false
            if (name.isBlank()) {
                binding.tilName.error = getString(R.string.name_required)
                error = true
            }
            if (phoneNumber.isBlank()) {
                binding.tilEmail.error = getString(R.string.password_required)
                error = true
            }
            if (email.isBlank()) {
                binding.tilPhoneNumber.error = getString(R.string.phone_number_required)
                error = true
            }
            if (!error) {
                val newContact =
                    ContactData(
                        contactId = 0,
                        image = photoFile.absolutePath,
                        name = name,
                        email = email,
                        phoneNumber = phoneNumber
                    )
                contactsViewModel.saveContact(newContact)
                Toast.makeText(this, (getString(R.string.contact_saved)), Toast.LENGTH_SHORT).show()
                finish()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }

        }

    fun clearErrors() {
        binding.tilName.error = null
        binding.tilPhoneNumber.error = null
        binding.tilEmail.error = null
    }
    }





