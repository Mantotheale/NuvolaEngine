package nuvola.render.vertex.layout;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_INT;

public enum OpenGLType {
    INT {
        @Override
        public int size() {
            return Integer.BYTES;
        }

        @Override
        public int glEnum() {
            return GL_INT;
        }
    },
    FLOAT {
        @Override
        public int size() {
            return Float.BYTES;
        }

        @Override
        public int glEnum() {
            return GL_FLOAT;
        }
    },
    BYTE {
        @Override
        public int size() {
            return Byte.BYTES;
        }

        @Override
        public int glEnum() {
            return GL_BYTE;
        }
    },
    UNSIGNED_INT {
        @Override
        public int size() {
            return Integer.BYTES;
        }

        @Override
        public int glEnum() {
            return GL_UNSIGNED_INT;
        }
    };

    public abstract int size();
    public abstract int glEnum();
}
