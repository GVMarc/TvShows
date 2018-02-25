### Default
-optimizationpasses 5
-optimizations !code/simplification/arithmetic,!code/simplification/cast,!field/*,!class/merging/*
-allowaccessmodification
-repackageclasses ''
-renamesourcefileattribute SourceFile
-keepattributes SourceFile,LineNumberTable,Signature, *Annotation*


### Retrofit
# Retain generic type information for use by reflection by converters and adapters.
-keepattributes Signature
# Retain service method parameters.
-keepclassmembernames,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}
# Ignore annotation used for build tooling.
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement


### OKHTTP 3
-dontwarn okio.**
-dontwarn javax.annotation.Nullable
-dontwarn javax.annotation.ParametersAreNonnullByDefault


### GSON
# Gson specific classes
-keep class sun.misc.Unsafe { *; }
# Application classes that will be serialized/deserialized over Gson
-keep com.gvmarc.tvshows.data.entity.** { *; }
# Prevent proguard from stripping interface information from TypeAdapterFactory,
# JsonSerializer, JsonDeserializer instances (so they can be used in @JsonAdapter)
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer


### PICASSO
-dontwarn com.squareup.okhttp.**