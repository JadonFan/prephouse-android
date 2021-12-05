package com.prephouse.annotationProcessor.room

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.TypeSpec
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement

class ConvertibleProcessor : AbstractProcessor() {
    override fun process(
        annotations: MutableSet<out TypeElement>?,
        roundEnv: RoundEnvironment?
    ): Boolean {
        roundEnv?.getElementsAnnotatedWith(Convertible::class.java)?.forEach { element ->
            val elementPackage = processingEnv.getElementUtils().getPackageOf(element).toString()
            val enumClass = ClassName(elementPackage, element.simpleName.toString())

            val packageName = "com.prephouse.prephouse.databases.converters"
            val className = "${element.simpleName}ConvertersTemp"

            val typeConverterAnnotationName = ClassName("androidx.room", "TypeConverter")

            val converterGen = TypeSpec.objectBuilder(className)
                .addFunction(
                    FunSpec.builder("fromEnum")
                        .addAnnotation(typeConverterAnnotationName)
                        .addParameter(ParameterSpec.builder("value", Int::class).build())
                        .returns(enumClass)
                        .addStatement("return %T.values().single { it.value==value }", enumClass)
                        .build()
                )
                .addFunction(
                    FunSpec.builder("toValue")
                        .addAnnotation(typeConverterAnnotationName)
                        .addParameter(ParameterSpec.builder("enum", enumClass).build())
                        .returns(Int::class)
                        .addStatement("return enum.value", enumClass)
                        .build()
                )

            FileSpec.builder(packageName, className)
                .addType(converterGen.build())
                .build()
                .writeTo(processingEnv.filer)
        }

        return true
    }

    override fun getSupportedSourceVersion() = SourceVersion.latest()

    override fun getSupportedAnnotationTypes() = setOf(
        Convertible::class.java.name
    )
}
