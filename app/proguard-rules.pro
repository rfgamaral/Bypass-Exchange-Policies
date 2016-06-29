# Do not print notes about unused Google Play Licensing and Google Mobile Services
-dontnote **.vending.licensing.ILicensingService
-dontnote com.google.android.gms.**

# Do not print notes about duplicate definition of library classes
-dontnote android.net.**
-dontnote org.apache.http.**

# Do not print notes about Android Support libraries
-dontnote android.support.**

# Preserve attributes required for producing useful obfuscated stack traces
-renamesourcefileattribute SourceFile
-keepattributes SourceFile,LineNumberTable
