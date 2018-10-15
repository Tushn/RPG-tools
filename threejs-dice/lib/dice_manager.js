// MAIN

// standard global variables
var container, scene, camera, renderer, controls, stats, world, dice = [];
// var diceValues = [];
init();

var diceThrow, readValues;

// FUNCTIONS
function init()
{
	// SCENE
	scene = new THREE.Scene();
	// CAMERA
	var SCREEN_WIDTH = window.innerWidth/2, SCREEN_HEIGHT = window.innerHeight/2;
	var VIEW_ANGLE = 45, ASPECT = SCREEN_WIDTH / SCREEN_HEIGHT, NEAR = 0.01, FAR = 20000;
	camera = new THREE.PerspectiveCamera( VIEW_ANGLE, ASPECT, NEAR, FAR);
	scene.add(camera);
	camera.position.set(0,30,30);
	// RENDERER
    renderer = new THREE.WebGLRenderer( {antialias:true} );
	renderer.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
    renderer.shadowMap.enabled = true;
    renderer.shadowMap.type = THREE.PCFSoftShadowMap;

	container = document.getElementById( 'ThreeJS' );
	container.appendChild( renderer.domElement );
	// EVENTS
	// CONTROLS
	controls = new THREE.OrbitControls( camera, renderer.domElement );
	// STATS
	stats = new Stats();
	/*stats.domElement.style.position = 'absolute';
	stats.domElement.style.bottom = '0px';
	stats.domElement.style.zIndex = 100;
	container.appendChild( stats.domElement );
	*/
	var ambient = new THREE.AmbientLight('#ffffff', 0.1);
	scene.add(ambient);

    var directionalLight = new THREE.DirectionalLight('#ffffff', 0.2);
    directionalLight.position.x = -1000;
    directionalLight.position.y = 1000;
    directionalLight.position.z = 1000;
    scene.add(directionalLight);

    var light = new THREE.SpotLight(0xefdfd5, 1.3);
    light.position.y = 100;
    light.target.position.set(0, 0, 0);
    light.castShadow = true;
    light.shadow.camera.near = 50;
    light.shadow.camera.far = 110;
    light.shadow.mapSize.width = 1024;
    light.shadow.mapSize.height = 1024;
    scene.add(light);


	// FLOOR
	// var floorMaterial = new THREE.MeshPhongMaterial( { color: '#00aa00', side: THREE.DoubleSide } );
	var floorMaterial = new THREE.MeshPhongMaterial( { color: '#cccccc', side: THREE.DoubleSide } );
	var floorGeometry = new THREE.PlaneGeometry(30, 30, 10, 10);
	var floor = new THREE.Mesh(floorGeometry, floorMaterial);
	floor.receiveShadow  = true;
	floor.rotation.x = Math.PI / 2;
	scene.add(floor);
	// SKYBOX/FOG
	var skyBoxGeometry = new THREE.CubeGeometry( 10000, 10000, 10000 );
	var skyBoxMaterial = new THREE.MeshPhongMaterial( { color: 0x9999ff, side: THREE.BackSide } );
	var skyBox = new THREE.Mesh( skyBoxGeometry, skyBoxMaterial );
	// scene.add(skyBox);
	scene.fog = new THREE.FogExp2( 0x9999ff, 0.00025 );

	////////////
	// CUSTOM //
	////////////
    world = new CANNON.World();

    world.gravity.set(0, -9.82 * 20, 0);
    world.broadphase = new CANNON.NaiveBroadphase();
    world.solver.iterations = 16;

    DiceManager.setWorld(world);

    //Floor
    var floorBody = new CANNON.Body({mass: 0, shape: new CANNON.Plane(), material: DiceManager.floorBodyMaterial});
    floorBody.quaternion.setFromAxisAngle(new CANNON.Vec3(1, 0, 0), -Math.PI / 2);
    world.add(floorBody);

    //Walls
	var wallMaterial = new THREE.MeshPhongMaterial( { color: '#ffcccc', side: THREE.DoubleSide } );
	var wall = new THREE.Mesh(floorGeometry, floorMaterial);
	wall.receiveShadow  = true;
	wall.rotation.y = Math.PI / 2;
	wall.position.x = 15;
	// wall.translate(0,1,1);
	scene.add(wall);
	
	var wallBody = new CANNON.Body({mass: 0, shape: new CANNON.Plane(), material: DiceManager.floorBodyMaterial});
    wallBody.quaternion.setFromAxisAngle(new CANNON.Vec3(0, 1, 0), -Math.PI / 2);
	wallBody.position.x = 15;
    world.add(wallBody);
	
	var dN = [DiceD4, DiceD6, DiceD8, DiceD10, DiceD12, DiceD20], dFaces = [4, 6, 8, 10, 12, 20];
    var colors = ['#ff0000', '#ffff00', '#00ff00', '#0000ff', '#ff00ff', '#00ffff'];
	var diceInit = [];

	for(var i=0; i<dN.length; i++){
		diceInit.push([]);
		for(var j=0; j<6; j++){
			diceInit[i].push(new dN[i]({size: 1.5, backColor: colors[i]}));
			diceInit[i][j].countFaces = dFaces[i];
			diceInit[i][j].getObject().position.y = -10;
			scene.add(diceInit[i][j].getObject());
		}
		diceInit[i].count = 0;
	}
	
    function randomDiceThrow() {
		for(var i=0; i<dN.length; i++)
			for(var j=0; j<6; j++){
				diceInit[i][j].getObject().position.x = 0;
				diceInit[i][j].getObject().position.y = -10;
			}
		/*
		for(var i=0; i<dice.length;i++){
			scene.remove(dice[i].getObject());
			//dice[i].geometry.dispose();
			// dice[i].material.dispose();
			// myMesh = undefined;
			delete(dice[i]);
		}
		*/
		for(var i=0; i<dN.length; i++)
			diceInit[i].count = 0;
		
		
		dice = [];

		for(var i=0, dnCount=0; i<$(".dice_control").length; i++){
			dnCount = Number($($(".dice_control").get(i)).children("input").val());
			
			for(var j=0; j<dnCount; j++)
				dice.push(diceInit[i][diceInit[i].count++]);
		}
		
        // var diceValues = [];

        for (var i = 0; i < dice.length; i++) {
            var yRand = Math.random() * 20
            dice[i].getObject().position.x = -15 - (i % 3) * 1.5;
            dice[i].getObject().position.y = 2 + Math.floor(i / 3) * 1.5;
            dice[i].getObject().position.z = -15 + (i % 3) * 1.5;
            dice[i].getObject().quaternion.x = (Math.random()*90-45) * Math.PI / 180;
            dice[i].getObject().quaternion.z = (Math.random()*90-45) * Math.PI / 180;
            dice[i].updateBodyFromMesh();
            var rand = Math.random() * 5;
            dice[i].getObject().body.velocity.set(25 + rand, 40 + yRand, 15 + rand);
            dice[i].getObject().body.angularVelocity.set(20 * Math.random() -10, 20 * Math.random() -10, 20 * Math.random() -10);

            //diceValues.push({dice: dice[i], value: i + 1});
        }

        //DiceManager.prepareValues(diceValues);
    }

	readValues = function(){
		$(".dice_result").text('');
		if(dice.length>0)
			$(".dice_result").get(dFaces.indexOf(dice[0].countFaces)).textContent += '  '+dice[0].getUpsideValue();
		for(var i=1; i<dice.length; i++)
			$(".dice_result").get(dFaces.indexOf(dice[i].countFaces)).textContent += ", "+ dice[i].getUpsideValue();
	}
    // setInterval(randomDiceThrow, 3000);
    // randomDiceThrow();
    diceThrow = randomDiceThrow;
    requestAnimationFrame( animate );
}

function animate()
{
    updatePhysics();
	render();
	update();

    requestAnimationFrame( animate );

}

function updatePhysics() {
    world.step(1.0 / 60.0);

    for (var i in dice) {
        dice[i].updateMeshFromBody();
    }
}

function update()
{
	controls.update();
	stats.update();
}

function render()
{
	renderer.render( scene, camera );
}

