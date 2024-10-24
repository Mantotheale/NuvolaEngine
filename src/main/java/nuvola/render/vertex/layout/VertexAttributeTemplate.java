package nuvola.render.vertex.layout;

public record VertexAttributeTemplate(OpenGLType type, int count) {
    public int size() {
        return type.size() * count;
    }

    public final static VertexAttributeTemplate POSITION_2D = new VertexAttributeTemplate(OpenGLType.FLOAT, 2);
    public final static VertexAttributeTemplate POSITION_3D = new VertexAttributeTemplate(OpenGLType.FLOAT, 3);
    public final static VertexAttributeTemplate COLOR_3C = new VertexAttributeTemplate(OpenGLType.FLOAT, 3);
    public final static VertexAttributeTemplate COLOR_4C = new VertexAttributeTemplate(OpenGLType.FLOAT, 4);
    public final static VertexAttributeTemplate TEX_COORDS_2D = new VertexAttributeTemplate(OpenGLType.FLOAT, 2);
    public final static VertexAttributeTemplate TEX_COORDS_3D = new VertexAttributeTemplate(OpenGLType.FLOAT, 3);
    public final static VertexAttributeTemplate NORMAL_3D = new VertexAttributeTemplate(OpenGLType.FLOAT, 3);
}
