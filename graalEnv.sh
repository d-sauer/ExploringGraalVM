docker run -it --rm --name graalvm_env -v "$PWD":/opt/project -v "$HOME"/.m2:/root/.m2 graal_build_image /bin/bash
