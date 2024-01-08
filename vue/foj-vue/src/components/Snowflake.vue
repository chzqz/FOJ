<template>
    <div id="app">
      <div v-for="flake in flakes" :key="flake.id" :style="flake.style" class="flake">*</div>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        flakes: []
      }
    },
    mounted() {
      this.createFlakes();
      setInterval(this.moveFlakes, 100);
    },
    methods: {
      createFlakes() {
        for (let i = 0; i < 200; i++) {
          const x = Math.random() * window.innerWidth;
          const y = Math.random() * window.innerHeight;
          const speed = Math.random() * 3 + 2;
          const size = Math.random() * 3 + 2;
          const id = i;
          this.flakes.push({x, y, speed, size, id});
        }
      },
      moveFlakes() {
        this.flakes.forEach(flake => {
          flake.y += flake.speed;
          if (flake.y > window.innerHeight) {
            flake.y = 0;
          }
          flake.style = `left: ${flake.x}px; top: ${flake.y}px; font-size: ${flake.size}px`;
        });
      }
    }
  }
  </script>
  
  <style>
  .flake {
    position: absolute;
    color: #2d165a;
  }
  </style>