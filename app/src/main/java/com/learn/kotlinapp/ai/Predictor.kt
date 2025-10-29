package com.learn.kotlinapp.ai
//
//import org.intel.openvino.Core
//import org.intel.openvino.InferRequest
//import org.intel.openvino.Tensor
//import android.content.Context
//
//import java.nio.FloatBuffer
//
//
//open class BirdPredictor(context: Context) {
//    private val core = Core()
//    private val modelPath = "${context.filesDir}/bird_model.xml" // FP16 OpenVINO IR model
//    private val compiledModel = core.compileModel(modelPath, "CPU")
//    private val inferRequest: InferRequest = compiledModel.createInferRequest()
//
//    fun predict(inputData: FloatArray): FloatArray {
//        val inputTensor = Tensor.fromData(inputData, longArrayOf(1, inputData.size.toLong()))
//        inferRequest.setInputTensor(inputTensor)
//        inferRequest.infer()
//        val outputTensor = inferRequest.outputTensor
//        return outputTensor.data<FloatArray>()
//    }
//}
