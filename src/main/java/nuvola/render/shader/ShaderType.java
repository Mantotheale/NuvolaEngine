package nuvola.render.shader;

import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;

public enum ShaderType {
    VERTEX_SHADER {
        @Override
        public int glType() {
            return GL_VERTEX_SHADER;
        }
    },
    FRAGMENT_SHADER {
        @Override
        public int glType() {
            return GL_FRAGMENT_SHADER;
        }
    };

    public abstract int glType();
}
