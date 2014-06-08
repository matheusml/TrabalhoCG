package util.camera;

import util.math.Matrix4f;
import util.math.Vector3f;
import util.math.Vector4f;

/**
 *
 * @author mlage
 */
public class Camera {

    private Vector4f cameraEye = new Vector4f();
    private Vector4f cameraAt  = new Vector4f(); 
    private Vector4f cameraUp  = new Vector4f();

    public Camera(Vector4f eye, Vector4f at, Vector4f up) {
        cameraEye = eye;
        cameraAt  = at;
        cameraUp  = up;
    }

    public Matrix4f viewMatrix() {

        float eyeX = cameraEye.x; float eyeY = cameraEye.y; float eyeZ = cameraEye.z;
        float atX  = cameraAt.x;  float atY  = cameraAt.y;  float atZ  = cameraAt.z;
        float upX  = cameraUp.x;  float upY  = cameraUp.y;  float upZ  = cameraUp.z;
        
        //n = eye  - at
        Vector3f tempViewN = new Vector3f();
        tempViewN.setTo(eyeX, eyeY, eyeZ);
        tempViewN.subtract(atX, atY, atZ);
        tempViewN.normalize();
        
        Vector3f tempViewUP = new Vector3f();
        tempViewUP.setTo(upX, upY, upZ);
        
        //u = side
        Vector3f tempViewU = new Vector3f();
        tempViewUP.crossProduct(tempViewN, tempViewU);
        tempViewU.normalize();
        
        //v = up
        Vector3f tempViewV = new Vector3f();
        tempViewN.crossProduct(tempViewU, tempViewV);
        tempViewV.normalize();

        Matrix4f tempRotation = new Matrix4f();
        tempRotation.m11 = tempViewU.x;  tempRotation.m21 = tempViewU.y;  tempRotation.m31 = tempViewU.z;  tempRotation.m41 = 0.0f;
        tempRotation.m12 = tempViewV.x;  tempRotation.m22 = tempViewV.y;  tempRotation.m32 = tempViewV.z;  tempRotation.m42 = 0.0f;
        tempRotation.m13 = tempViewN.x;  tempRotation.m23 = tempViewN.y;  tempRotation.m33 = tempViewN.z;  tempRotation.m43 = 0.0f;
        tempRotation.m14 = 0.0f;         tempRotation.m24 = 0.0f;         tempRotation.m34 = 0.0f;         tempRotation.m44 = 1.0f;
        
        Matrix4f tempTranslation = new Matrix4f();
        tempTranslation.m41 = -eyeX;
        tempTranslation.m42 = -eyeY;
        tempTranslation.m43 = -eyeZ;
        
        tempRotation.multiply(tempTranslation);
        
        return tempRotation;
    }
}
