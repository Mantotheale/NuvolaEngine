#version 330 core

in vec2 texture_coordinates;

uniform sampler2D u_texture;

void main() {
    gl_FragColor = texture2D(u_texture, texture_coordinates);
}