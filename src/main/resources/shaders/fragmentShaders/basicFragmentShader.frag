#version 330 core

in vec2 texture_coordinates;

uniform sampler2D tex;

void main() {
    gl_FragColor = texture2D(tex, texture_coordinates);
}