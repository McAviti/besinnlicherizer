import os
import azure.ai.vision as sdk
import random

def analyze_image(imagepath):

    xmas1 = [" with many lighted candles on it. ",
             " decorated like a bright christmas tree. ",
             " buried in a pile of christmas cookies. ",
             " wrapped as a christmas present. ",
             " having a large logo of the austrian post like https://upload.wikimedia.org/wikipedia/commons/thumb/e/e8/Post_Horn_RGB.svg/375px-Post_Horn_RGB.svg.png on it. "]

    xmas2 = [" covered deeply in white snow. "
             " like a huge winter landscape. ",
             " depicted as a sparkling christmas scene. ",
             " illuminated in a variety of christmas decorated lights. ",
             " with christmas trees everywhere. "]

    service_options = sdk.VisionServiceOptions("https://kdi-postat-image-vision.cognitiveservices.azure.com/",
                                               "169c011247594e04a0685360434cc11f")

    vision_source = sdk.VisionSource(filename=imagepath)
    # vision_source = sdk.VisionSource(
    #    url="https://learn.microsoft.com/azure/ai-services/computer-vision/media/quickstarts/presentation.png")

    analysis_options = sdk.ImageAnalysisOptions()

    analysis_options.features = (
        sdk.ImageAnalysisFeature.CAPTION |
        sdk.ImageAnalysisFeature.OBJECTS |
        sdk.ImageAnalysisFeature.DENSE_CAPTIONS
    )

    analysis_options.language = "en"
    analysis_options.gender_neutral_caption = True
    image_analyzer = sdk.ImageAnalyzer(service_options, vision_source, analysis_options)
    result = image_analyzer.analyze()

    prompt = "create an image that looks like "
    if result.reason == sdk.ImageAnalysisResultReason.ANALYZED:

        if result.caption is not None:
            print(" Caption:")
            print("   '{}', Confidence {:.4f}".format(result.caption.content, result.caption.confidence))
            prompt += result.caption.content + ".\n"

        if result.objects is not None:
            print(" Objects:")
            prompt += "There are several objects to be seen:\n"
            for object in result.objects:
                print("   Object: '{}'".format(object.name))
                prompt += "  " + object.name + xmas1[random.randrange(0,4)] + "\n"

        if result.dense_captions is not None:
            print(" Dense Captions:")
            prompt += "People describe the scene as\n"
            for dense_caption in result.dense_captions:
                print("   Dense Caption: '{}'".format(dense_caption.content))
                prompt += "  " + dense_caption.content + xmas2[random.randrange(0,4)] + "\n"

        return prompt
    else:
        error_details = sdk.ImageAnalysisErrorDetails.from_result(result)
        print(" Analysis failed.")
        print("   Error reason: {}".format(error_details.reason))
        print("   Error code: {}".format(error_details.error_code))
        print("   Error message: {}".format(error_details.message))
        return None