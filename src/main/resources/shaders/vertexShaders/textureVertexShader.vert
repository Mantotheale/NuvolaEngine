#version 330 core

layout (location = 0) in vec3 pos;
layout (location = 1) in vec2 texCoord;

out vec2 texture_coordinates;

void main() {
    gl_Position = vec4(pos, 1);
    texture_coordinates = texCoord;
}