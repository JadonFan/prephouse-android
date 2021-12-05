package com.prephouse.annotationProcessor.serialization

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.TypeSpec
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.SourceVersion
import javax.lang.model.element.ElementKind
import javax.lang.model.element.TypeElement

class SerializableEnumProcessor : AbstractProcessor() {
    override fun process(
        annotations: MutableSet<out TypeElement>?,
        roundEnv: RoundEnvironment?
    ): Boolean {
        roundEnv?.getElementsAnnotatedWith(SerializableEnum::class.java)?.forEach { element ->
            val serialNumberedEnumGen = TypeSpec.enumBuilder("${element.simpleName}Serial")
                .addAnnotation(ClassName("kotlinx.serialization", "Serializable"))
            val packName = processingEnv.elementUtils.getPackageOf(element).toString()
            val className = element.simpleName.toString()

            val serialNameAnnotationName = ClassName("kotlinx.serialization", "SerialName")
            (element.enclosedElements.filter { it.kind == ElementKind.ENUM_CONSTANT })
                .forEachIndexed { index, enumConstant ->
                    serialNumberedEnumGen
                        .addEnumConstant(enumConstant.simpleName.toString())
                }

            FileSpec.builder(packName, "${className}Serial")
                .addType(serialNumberedEnumGen.build())
                .build()
                .writeTo(processingEnv.filer)
        }

        return true
    }

    override fun getSupportedSourceVersion() = SourceVersion.latest()

    override fun getSupportedAnnotationTypes() = setOf(
        SerializableEnum::class.java.name
    )
}
