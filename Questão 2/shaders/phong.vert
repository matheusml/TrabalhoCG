#version 330

uniform mat4 NormalMatrix;
uniform mat4 ModelViewMatrix;
uniform mat4 ProjectionMatrix;

uniform vec4 eyePosition;

layout (location = 0) in vec4 position;
layout (location = 1) in vec4 normal;

smooth out vec4 theColor;

void main()
{
    vec3 normalCam = normalize(vec3(NormalMatrix * normal));
    vec3 positionCam = vec3(ModelViewMatrix * position);
    vec3 eyePositionCam = vec3(ModelViewMatrix * eyePosition); // must be the origin

    // fixed point light properties
    vec3 lightPosition0Cam = vec3(0.0f, 2.0f, 0.0f);
    vec3 lightPosition1Cam = vec3(0.0f,-2.0f, 0.0f);

    vec3 Ls = vec3(1.0f, 1.0f, 1.0f);  // white specular colour
    vec3 Ld = vec3(0.6f, 0.6f, 0.6f);  // dull white diffuse light colour
    vec3 La = vec3(0.4f, 0.4f, 0.4f);  // grey ambient colour

    vec3 Ks = vec3 (1.0f, 1.0f, 1.0f); // fully reflect specular light
    vec3 Kd = vec3 (0.5f, 0.5f, 0.5f); // partially reflect difuse light
    vec3 Ka = vec3 (0.3f, 0.3f, 0.3f); // partially reflect ambient light
    float specularExponent = 180.0f;   // specular 'power'

    // Calculo da iluminacao
    // ambient intensity
    vec3 Ia = La * Ka;

    // diffuse intensity
    vec3 distanceToLight0Cam = lightPosition0Cam - positionCam;
    vec3 distanceToLight1Cam = lightPosition1Cam - positionCam;

    vec3 directionToLight0Cam = normalize(distanceToLight0Cam);
    vec3 directionToLight1Cam = normalize(distanceToLight1Cam);

    float dotProd0 = dot(directionToLight0Cam, normalCam);
    float dotProd1 = dot(directionToLight1Cam, normalCam);
    dotProd0 = max(dotProd0, 0.0f);
    dotProd1 = max(dotProd1, 0.0f);
    
    vec3 Id = Ld * Kd * (dotProd0 + dotProd1); // final diffuse intensity

    // specular intensity
    vec3 positionToEyeCam = normalize(eyePositionCam-positionCam);

    vec3 halfWayEye0Cam = normalize(positionToEyeCam + distanceToLight0Cam);
    vec3 halfWayEye1Cam = normalize(positionToEyeCam + distanceToLight1Cam);
    
    float dotProdSpecular0 = dot(halfWayEye0Cam, normalCam);
    dotProdSpecular0 = max(dotProdSpecular0, 0.0);

    float dotProdSpecular1 = dot(halfWayEye1Cam, normalCam);
    dotProdSpecular1 = max(dotProdSpecular1, 0.0);

    float specularFactor0 = pow(dotProdSpecular0, specularExponent);
    float specularFactor1 = pow(dotProdSpecular1, specularExponent);
   
    vec3 Is = Ls * Ks * (specularFactor0 + specularFactor1 ); // final specular intensity

    gl_Position = ProjectionMatrix * vec4(positionCam, 1.0f) ;//Posicao projetada do vertice
    theColor = vec4 ((Is + Id + Ia)*vec3(1.0f,0.2f,0.3f), 1.0f);//calculo final de iluminacao
}