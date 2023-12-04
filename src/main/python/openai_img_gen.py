from openai import OpenAI

def create_image(genprompt):

  print("---")
  print(">" + genprompt)
  print("---")

  client = OpenAI(api_key="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")

  response = client.images.generate(
    model="dall-e-3",
    prompt=genprompt,
    size="1024x1024",
    quality="standard",
    n=1,
  )

  image_url = response.data[0].url
  print("-->" + image_url)