package nuvola.render.material;

public enum MaterialParameter {
    AMBIENT {
        @Override
        public String paramName() {
            return "material.ambient";
        }
    },
    DIFFUSE {
        @Override
        public String paramName() {
            return "material.diffuse";
        }
    },
    SPECULAR {
        @Override
        public String paramName() {
            return "material.specular";
        }
    };

    public abstract String paramName();
}
