package com.xtivia.swaggercodegen

class PluginExtension {
    String inputSpec
    String output
    String language
    Map<String,Object> additionalProperties
    String models
    String apis
    String supportingFiles
    Boolean cleanOutputDir = true
}
