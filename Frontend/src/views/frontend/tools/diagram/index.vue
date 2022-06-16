<template>
  <Layout>
    <div class="d-xl-flex">
      <div class="w-100">
        <div class="d-md-flex">
          <div class="w-100">
            <div class="card">
              <div class="card-body">
                <div class="row mb-3">
                  <div class="col-xl-12 col-sm-12">
                    <div class="mt-2">
                      <h1>Diagram</h1>
                    </div>
                  </div>
                </div>
                <div>
                  <el-row style="margin-bottom:10px">
                     <div>
                      <ejs-toolbar
                        id="toolbar"
                        style="width: 100%; height: 10%; margin-top: 10px"
                        :clicked="toolbarclicked"
                        :items="toolbaritems"
                      >
                      </ejs-toolbar>
                    </div>
                  </el-row>

                  <el-row>
                    <div class="control-section" >
                      <div class="control-section" >
                        <div style="width: 100%; height: 80%">
                          <div id="palette-space" class="sb-mobile-palette">
                            <ejs-symbolpalette
                              id="symbolpalette"
                              :expandMode="paletteexpandMode"
                              :palettes="palettes"
                              :getNodeDefaults="palettegetNodeDefaults"
                              :symbolMargin="palettesymbolMargin"
                              :getSymbolInfo="palettegetSymbolInfo"
                              :width="palettewidth"
                              :height="paletteheight"
                              :symbolHeight="palettesymbolHeight"
                              :symbolWidth="palettesymbolWidth"
                            />
                          </div>

                          <div id="diagram-space" class="sb-mobile-diagram">
                            <ejs-diagram
                              style="display: block"
                              ref="diagramObj"
                              id="diagram"
                              :width="width"
                              :height="height"
                              :snapSettings="snapSettings"
                              :getConnectorDefaults="getConnectorDefaults"
                            />
                          </div>
                          <div id="upload-container">
                            <ejs-uploader
                              id="fileupload"
                              name="UploadFiles"
                              :asyncSettings="fileuploadasyncSettings"
                              :success="fileuploadsuccess"
                              :showFileList="showFile"
                            />
                          </div>
                        </div>
                      </div>
                    </div>
                  </el-row>
                </div>
              </div>
            </div>
            <!-- end card -->
          </div>
          <!-- end w-100 -->
        </div>
      </div>
      
    </div>
   
  </Layout>
</template>

<script>
import Vue from "vue";
import {
  DiagramPlugin,
  SymbolPalettePlugin,
} from "@syncfusion/ej2-vue-diagrams";

import { UploaderPlugin } from "@syncfusion/ej2-vue-inputs";
import {
  ToolbarPlugin,
} from "@syncfusion/ej2-vue-navigations";

Vue.use(DiagramPlugin);
Vue.use(ToolbarPlugin);
Vue.use(UploaderPlugin);
Vue.use(SymbolPalettePlugin);

let diagramInstance;

import Layout from "@/layouts/main";


let interval;
interval = [
  1, 9, 0.25, 9.75, 0.25, 9.75, 0.25, 9.75, 0.25, 9.75, 0.25, 9.75, 0.25, 9.75,
  0.25, 9.75, 0.25, 9.75, 0.25, 9.75,
];
let gridlines = {
  lineColor: "#e0e0e0",
  lineIntervals: interval,
};

export default {
  components: {
    Layout,
  },
  data() {
    return {
      width: "100%",
      height: "700px",
     
      snapSettings: {
        horizontalGridlines: gridlines,
        verticalGridlines: gridlines,
      },
      getConnectorDefaults: (args, diagram) => {
        if (args.targetDecorator) {
          args.targetDecorator.height = 5;
          args.targetDecorator.width = 5;
          args.targetDecorator.style = {
            fill: "#797979",
            strokeColor: "#797979",
          };
        }
        if (args.style) args.style.strokeColor = "#797979";
        return args;
      },
      paletteexpandMode: "Multiple",
      palettes: [
        {
          id: "flow",
          expanded: true,
          symbols: flowshapes,
          iconCss: "shapes",
          title: "Flow Shapes",
        },
        {
          id: "connectors",
          expanded: true,
          symbols: connectorSymbols,
          iconCss: "shapes",
          title: "Connectors",
        },
      ],
      //set default value for Node.
      palettegetNodeDefaults: (symbol) => {
        symbol.style.strokeColor = "#757575";
        if (symbol.id === "Terminator" || symbol.id === "Process") {
          symbol.width = 80;
          symbol.height = 40;
        } else if (
          symbol.id === "Decision" ||
          symbol.id === "Document" ||
          symbol.id === "PreDefinedProcess" ||
          symbol.id === "PaperTap" ||
          symbol.id === "DirectData" ||
          symbol.id === "MultiDocument" ||
          symbol.id === "Data"
        ) {
          symbol.width = 50;
          symbol.height = 40;
        }
        if (symbol.style) symbol.style.strokeWidth = 2;
      },
      palettesymbolMargin: { left: 15, right: 15, top: 15, bottom: 15 },
      palettegetSymbolInfo: (symbol) => {
        return { fit: true };
      },
      palettewidth: "100%",
      paletteheight: "700px",
      palettesymbolHeight: 60,
      palettesymbolWidth: 60,

      toolbarclicked: (args) => {
        if (args.item.text === "New") {
          diagramInstance.clear();
        } else if (args.item.text === "Load") {
          let element = document.getElementsByClassName("e-file-select-wrap");
          let htmlButtonElement = element[0].querySelector("button");
          htmlButtonElement.click();
        } else if (args.item.id === "palette-icon") {
          openPalette();
        } else {
          download(diagramInstance.saveDiagram());
        }
      },
      toolbaritems: [
        { text: "New", tooltipText: "New" },
        {
          type: "Separator",
        },
        {
          text: "Save",
          tooltipText: "Save",
        },
        {
          type: "Separator",
        },
        {
          text: "Load",
          tooltipText: "Load",
        },
      ],
      fileuploadasyncSettings: {
        saveUrl: "https://aspnetmvc.syncfusion.com/services/api/uploadbox/Save",
        removeUrl:
          "https://aspnetmvc.syncfusion.com/services/api/uploadbox/Remove",
      },
      fileuploadsuccess: onUploadSuccess,
      showFile: true,
    };
  },
  mounted: function () {
    diagramInstance = this.$refs.diagramObj.ej2Instances;
    diagramInstance.fitToPage();
  },
};

//save the diagram object in json data.
function download(data) {
  let dataStr = "data:text/json;charset=utf-8," + encodeURIComponent(data);
  let a = document.createElement("a");
  a.href = dataStr;
  a.download = "Diagram.json";
  document.body.appendChild(a);
  a.click();
}

function onUploadSuccess(args) {
  let file1 = args.file;
  let file = file1.rawFile;
  let reader = new FileReader();
  reader.readAsText(file);
  reader.onloadend = loadDiagram;
}

//Load the diagraming object.
function loadDiagram(event) {
  diagramInstance.loadDiagram(event.target.result);
}

//Initialize the flowshapes for the symbol palatte
let flowshapes = [
  { id: "Terminator", shape: { type: "Flow", shape: "Terminator" } },
  { id: "Process", shape: { type: "Flow", shape: "Process" } },
  { id: "Decision", shape: { type: "Flow", shape: "Decision" } },
  { id: "Document", shape: { type: "Flow", shape: "Document" } },
  {
    id: "PreDefinedProcess",
    shape: { type: "Flow", shape: "PreDefinedProcess" },
  },
  { id: "PaperTap", shape: { type: "Flow", shape: "PaperTap" } },
  { id: "DirectData", shape: { type: "Flow", shape: "DirectData" } },
  { id: "SequentialData", shape: { type: "Flow", shape: "SequentialData" } },
  { id: "Sort", shape: { type: "Flow", shape: "Sort" } },
  { id: "MultiDocument", shape: { type: "Flow", shape: "MultiDocument" } },
  { id: "Collate", shape: { type: "Flow", shape: "Collate" } },
  { id: "SummingJunction", shape: { type: "Flow", shape: "SummingJunction" } },
  { id: "Or", shape: { type: "Flow", shape: "Or" } },
  { id: "InternalStorage", shape: { type: "Flow", shape: "InternalStorage" } },
  { id: "Extract", shape: { type: "Flow", shape: "Extract" } },
  { id: "ManualOperation", shape: { type: "Flow", shape: "ManualOperation" } },
  { id: "Merge", shape: { type: "Flow", shape: "Merge" } },
  {
    id: "OffPageReference",
    shape: { type: "Flow", shape: "OffPageReference" },
  },
  {
    id: "SequentialAccessStorage",
    shape: { type: "Flow", shape: "SequentialAccessStorage" },
  },
  { id: "Annotation", shape: { type: "Flow", shape: "Annotation" } },
  { id: "Annotation2", shape: { type: "Flow", shape: "Annotation2" } },
  { id: "data", shape: { type: "Flow", shape: "Data" } },
  { id: "Card", shape: { type: "Flow", shape: "Card" } },
  { id: "Delay", shape: { type: "Flow", shape: "Delay" } },
  
];
//Initializes connector symbols for the symbol palette
let connectorSymbols = [
  {
    id: "Link1",
    type: "Orthogonal",
    sourcePoint: { x: 0, y: 0 },
    targetPoint: { x: 40, y: 40 },
    targetDecorator: {
      shape: "Arrow",
      style: { fill: "#757575", strokeColor: "#757575" },
    },
    style: { strokeWidth: 2, strokeColor: "#757575" },
  },
  {
    id: "link2",
    type: "Orthogonal",
    sourcePoint: { x: 0, y: 0 },
    targetPoint: { x: 40, y: 40 },
    style: { strokeWidth: 2, strokeColor: "#757575" },
    targetDecorator: { shape: "None" },
  },
  {
    id: "Link3",
    type: "Straight",
    sourcePoint: { x: 0, y: 0 },
    targetPoint: { x: 40, y: 40 },
    targetDecorator: {
      shape: "Arrow",
      style: { fill: "#757575", strokeColor: "#757575" },
    },
    style: { strokeWidth: 2, strokeColor: "#757575" },
  },
  {
    id: "link4",
    type: "Straight",
    sourcePoint: { x: 0, y: 0 },
    targetPoint: { x: 40, y: 40 },
    style: { strokeWidth: 2, strokeColor: "#757575" },
    targetDecorator: { shape: "None" },
  },
  {
    id: "link5",
    type: "Bezier",
    sourcePoint: { x: 0, y: 0 },
    targetPoint: { x: 40, y: 40 },
    style: { strokeWidth: 2, strokeColor: "#757575" },
    targetDecorator: { shape: "None" },
  },
  {
      id: "Link6",
      type: "Orthogonal",
      sourcePoint: { x: 0, y: 0 },
      targetPoint: { x: 40, y: 40 },
      targetDecorator: { shape: "Arrow", style: {strokeColor: "#757575", fill: "#757575"} },
      style: { strokeWidth: 2, strokeDashArray: "4 4", strokeColor: "#757575" }
  },
 {
      id: "link7",
      sourcePoint: { x: 0, y: 0 },
      targetPoint: { x: 40, y: 40 },
      type: "Orthogonal",
      targetDecorator: { style: {strokeColor: "#757575", fill: "#757575"} },
      shape: {
        type: "Bpmn",
        flow: "Association",
        association: "Directional"
      },
      style: {
        strokeDashArray: "2,2", strokeColor: "#757575", fill: "#757575"
      }
    }
];

function openPalette() {
  let paletteSpace = document.getElementById("palette-space");
  let isMobile = window.matchMedia("(max-width:550px)").matches;
  if (isMobile) {
    if (!paletteSpace.classList.contains("sb-mobile-palette-open")) {
      paletteSpace.classList.add("sb-mobile-palette-open");
    } else {
      paletteSpace.classList.remove("sb-mobile-palette-open");
    }
  }
}
</script>

<style>

.e-accordion {
    -webkit-tap-highlight-color: transparent;
    background: #fff;
    border: 1px solid #dee2e6;
    border-radius: 4px;
}
.e-hscroll-content{
  
  float:left !important;
}
.e-toolbar
  .e-toolbar-items
  .e-toolbar-item {
  margin-right: 5px;
  float:left !important;
}
.e-tbar-btn{
  color: #909399;
  background: #f4f4f5;
  border-color: #d3d4d6;
  margin-left: 10px;
  display: inline-block;
    line-height: 1;
    white-space: nowrap;
    cursor: pointer;
    background: #fff;
    border: 1px solid #dcdfe6;
    color: #606266;
    -webkit-appearance: none;
    text-align: center;
    box-sizing: border-box;
    outline: none;
    margin: 0;
    transition: .1s;
    font-weight: 500;
    padding: 12px 20px;
    font-size: 14px;
    border-radius: 4px;
}
.e-tbar-btn:hover{
  background: #909399;
    border-color: #909399;
    color: #fff;
}

.e-acrdn-header{
  background: #e7f1ff;
  border-bottom: 1px solid #dee2e6;
  line-height: 22px;
  min-height: 38px;
  overflow: hidden;
  padding: 7px 12px;
  text-decoration: none;
  text-overflow: ellipsis;
  white-space: nowrap;

}

.e-acrdn-header-icon{
  color: #6c757d;
}

.e-acrdn-header-content {
    color: #0d6efd;
    font-weight: bold;
}

.e-toggle-icon {
    display: table;
    font-size: 18px;
    height: 38px;
    min-height: 38px;
    min-width: 22px;
    position: absolute;
    right: 16px;
    top: 0;
}
.e-icons {
    display: table-cell;
    text-align: center;
    vertical-align: middle;
    transform: rotate(-180deg);
}
.e-tgl-collapse-icon::before {
    content: '\e729';
}



</style>

<style>
@import "../../../../../node_modules/@syncfusion/ej2-vue-diagrams/styles/material.css"; 

.e-file-select-wrap {
  display: none;
}
#upload-container {
  display: none;
}
.control-section {
  padding-top: 0px;
  padding-right: 0px;
}

.e-ddb-icons {
  font-family: "e-ddb-icons";
  font-size: 55px;
  font-style: normal;
  font-weight: normal;
  font-variant: normal;
  text-transform: none;
  line-height: 1;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}

.e-new::before {
  content: "\e70f";
}

.e-save::before {
  content: "\e710";
}

.e-open::before {
  content: "\e713";
}

.material .e-new::before {
  content: "\e704";
}

.material .e-save::before {
  content: "\e705";
}

.material .e-open::before {
  content: "\e708";
}

.bootstrap .e-new::before {
  content: "\e71c";
}

.bootstrap .e-save::before {
  content: "\e71d";
}

.bootstrap .e-open::before {
  content: "\e720";
}

.e-toolbar
  .e-toolbar-items
  .e-toolbar-item
  .e-tbar-btn.e-btn.e-tbtn-txt
  .e-icons.e-btn-icon {
  padding: 3px;
}

.sb-mobile-palette {
  width: 240px;
  height: 100%;
  float: left;
}

.sb-mobile-palette-bar {
  display: none;
}

.sb-mobile-diagram {
  width: calc(100% - 242px);
  height: 100%;
  float: left;
}

@media (max-width: 550px) {
  .sb-mobile-palette {
    z-index: 19;
    position: absolute;
    display: none;
    transition: transform 300ms linear, visibility 0s linear 300ms;
    width: 39%;
    height: 100%;
  }

  .sb-mobile-palette-bar {
    display: block;
    width: 100%;
    background: #fafafa;
    padding: 10px 10px;
    border: 0.5px solid #e0e0e0;
    min-height: 40px;
  }

  .sb-mobile-diagram {
    width: 100%;
    height: 100%;
    float: left;
    left: 0px;
  }

  #palette-icon {
    font-size: 20px;
  }
}

.sb-mobile-palette-open {
  position: absolute;
  display: block;
  right: 15px;
}

.e-ddb-icons2 {
  font-family: "e-ddb-icons2";
  font-size: 16px;
  font-style: normal;
  font-weight: normal;
  font-variant: normal;
  text-transform: none;
  line-height: 1;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}

.e-toggle-palette::before {
  content: "\e700" !important;
}

#palette-icon {
  display: none !important;
}

@media (max-width: 550px) {
  #palette-icon {
    display: inline-flex;
  }
}
</style>