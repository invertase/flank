package ftl.gc

import com.google.api.services.toolresults.ToolResults
import com.google.api.services.toolresults.model.Step
import com.google.testing.model.ToolResultsStep
import ftl.config.FtlConstants.JSON_FACTORY
import ftl.config.FtlConstants.applicationName
import ftl.config.FtlConstants.credential
import ftl.config.FtlConstants.httpTransport

object GcToolResults {

    private val service: ToolResults by lazy {
        ToolResults.Builder(httpTransport, JSON_FACTORY, credential)
                .setApplicationName(applicationName)
                .build()
    }

    fun getResults(toolResultsStep: ToolResultsStep): Step {
        return GcToolResults.service
                .projects()
                .histories()
                .executions()
                .steps()
                .get(
                        toolResultsStep.projectId,
                        toolResultsStep.historyId,
                        toolResultsStep.executionId,
                        toolResultsStep.stepId)
                .execute()
    }
}
