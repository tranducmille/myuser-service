package com.xtivia.swaggercodegen

import io.swagger.codegen.v3.ClientOptInput
import io.swagger.codegen.v3.ClientOpts
import io.swagger.codegen.v3.CodegenConfig
import io.swagger.codegen.v3.DefaultGenerator
import io.swagger.v3.parser.OpenAPIV3Parser
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class CodeGenTask extends DefaultTask {
    
    PluginExtension swagger

    @TaskAction
    def swaggerCodeGen() {
        // Configuration for language
        CodegenConfig config = forName(swagger.language)

        // Outputdir + clean
        config.setOutputDir(project.file(swagger.output ?: 'build/generated-sources/swagger').absolutePath)
        if (swagger.cleanOutputDir == true) {
        	project.delete(config.getOutputDir())
        }
        
        // Add additional properties
        config.additionalProperties()?.putAll(swagger.additionalProperties)

        if (swagger.apis != null) System.setProperty('apis', swagger.apis)
        if (swagger.models != null) System.setProperty('models', swagger.models)
        if (swagger.supportingFiles != null) System.setProperty('supportingFiles', swagger.supportingFiles)
        
        // Client input
        ClientOptInput input = new ClientOptInput()
                .opts(new ClientOpts())
                .openAPI(new OpenAPIV3Parser().read(swagger.inputSpec))
                .config(config)

        new DefaultGenerator().opts(input).generate()
    }

    private static CodegenConfig forName(String name) {
        ServiceLoader<CodegenConfig> loader = ServiceLoader.load(CodegenConfig.class)
        for (CodegenConfig config : loader) {
            if (config.getName().equals(name)) {
                return config
            }
        }

        // else try to load directly
        try {
            return (CodegenConfig) Class.forName(name).newInstance()
        } catch (Exception e) {
            throw new RuntimeException("Can't load config class with name ".concat(name), e)
        }
    }

}
