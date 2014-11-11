(function () {
    Polymer("core-toolbar", {
        justify: "", middleJustify: "", bottomJustify: "", justifyChanged: function (old) {
            this.updateBarJustify(this.$.topBar, this.justify, old)
        }, middleJustifyChanged: function (old) {
            this.updateBarJustify(this.$.middleBar, this.middleJustify, old)
        }, bottomJustifyChanged: function (old) {
            this.updateBarJustify(this.$.bottomBar, this.bottomJustify, old)
        }, updateBarJustify: function (bar, justify, old) {
            if (old) {
                bar.removeAttribute(this.toLayoutAttrName(old))
            }
            if (this.justify) {
                bar.setAttribute(this.toLayoutAttrName(justify), "")
            }
        }, toLayoutAttrName: function (value) {
            return value === "between" ? "justified" : value + "-justified"
        }
    })
})();
Polymer("core-selection", {
    multi: false, ready: function () {
        this.clear()
    }, clear: function () {
        this.selection = []
    }, getSelection: function () {
        return this.multi ? this.selection : this.selection[0]
    }, isSelected: function (item) {
        return this.selection.indexOf(item) >= 0
    }, setItemSelected: function (item, isSelected) {
        if (item !== undefined && item !== null) {
            if (isSelected) {
                this.selection.push(item)
            } else {
                var i = this.selection.indexOf(item);
                if (i >= 0) {
                    this.selection.splice(i, 1)
                }
            }
            this.fire("core-select", {isSelected: isSelected, item: item})
        }
    }, select: function (item) {
        if (this.multi) {
            this.toggle(item)
        } else if (this.getSelection() !== item) {
            this.setItemSelected(this.getSelection(), false);
            this.setItemSelected(item, true)
        }
    }, toggle: function (item) {
        this.setItemSelected(item, !this.isSelected(item))
    }
});
Polymer("core-selector", {
    selected: null,
    multi: false,
    valueattr: "name",
    selectedClass: "core-selected",
    selectedProperty: "",
    selectedAttribute: "active",
    selectedItem: null,
    selectedModel: null,
    selectedIndex: -1,
    excludedLocalNames: "",
    target: null,
    itemsSelector: "",
    activateEvent: "tap",
    notap: false,
    defaultExcludedLocalNames: "template",
    observe: {"selected multi": "selectedChanged"},
    ready: function () {
        this.activateListener = this.activateHandler.bind(this);
        this.itemFilter = this.filterItem.bind(this);
        this.excludedLocalNamesChanged();
        this.observer = new MutationObserver(this.updateSelected.bind(this));
        if (!this.target) {
            this.target = this
        }
    },
    get items() {
        if (!this.target) {
            return []
        }
        var nodes = this.target !== this ? this.itemsSelector ? this.target.querySelectorAll(this.itemsSelector) : this.target.children : this.$.items.getDistributedNodes();
        return Array.prototype.filter.call(nodes, this.itemFilter)
    },
    filterItem: function (node) {
        return !this._excludedNames[node.localName]
    },
    excludedLocalNamesChanged: function () {
        this._excludedNames = {};
        var s = this.defaultExcludedLocalNames;
        if (this.excludedLocalNames) {
            s += " " + this.excludedLocalNames
        }
        s.split(/\s+/g).forEach(function (n) {
            this._excludedNames[n] = 1
        }, this)
    },
    targetChanged: function (old) {
        if (old) {
            this.removeListener(old);
            this.observer.disconnect();
            this.clearSelection()
        }
        if (this.target) {
            this.addListener(this.target);
            this.observer.observe(this.target, {childList: true});
            this.updateSelected()
        }
    },
    addListener: function (node) {
        Polymer.addEventListener(node, this.activateEvent, this.activateListener)
    },
    removeListener: function (node) {
        Polymer.removeEventListener(node, this.activateEvent, this.activateListener)
    },
    get selection() {
        return this.$.selection.getSelection()
    },
    selectedChanged: function () {
        if (arguments.length === 1) {
            this.processSplices(arguments[0])
        } else {
            this.updateSelected()
        }
    },
    updateSelected: function () {
        this.validateSelected();
        if (this.multi) {
            this.clearSelection(this.selected);
            this.selected && this.selected.forEach(function (s) {
                this.setValueSelected(s, true)
            }, this)
        } else {
            this.valueToSelection(this.selected)
        }
    },
    validateSelected: function () {
        if (this.multi && !Array.isArray(this.selected) && this.selected != null) {
            this.selected = [this.selected]
        } else if (!this.multi && Array.isArray(this.selected)) {
            var s = this.selected[0];
            this.clearSelection([s]);
            this.selected = s
        }
    },
    processSplices: function (splices) {
        for (var i = 0, splice; splice = splices[i]; i++) {
            for (var j = 0; j < splice.removed.length; j++) {
                this.setValueSelected(splice.removed[j], false)
            }
            for (var j = 0; j < splice.addedCount; j++) {
                this.setValueSelected(this.selected[splice.index + j], true)
            }
        }
    },
    clearSelection: function (excludes) {
        this.$.selection.selection.slice().forEach(function (item) {
            var v = this.valueForNode(item) || this.items.indexOf(item);
            if (!excludes || excludes.indexOf(v) < 0) {
                this.$.selection.setItemSelected(item, false)
            }
        }, this)
    },
    valueToSelection: function (value) {
        var item = this.valueToItem(value);
        this.$.selection.select(item)
    },
    setValueSelected: function (value, isSelected) {
        var item = this.valueToItem(value);
        if (isSelected ^ this.$.selection.isSelected(item)) {
            this.$.selection.setItemSelected(item, isSelected)
        }
    },
    updateSelectedItem: function () {
        this.selectedItem = this.selection
    },
    selectedItemChanged: function () {
        if (this.selectedItem) {
            var t = this.selectedItem.templateInstance;
            this.selectedModel = t ? t.model : undefined
        } else {
            this.selectedModel = null
        }
        this.selectedIndex = this.selectedItem ? parseInt(this.valueToIndex(this.selected)) : -1
    },
    valueToItem: function (value) {
        return value === null || value === undefined ? null : this.items[this.valueToIndex(value)]
    },
    valueToIndex: function (value) {
        for (var i = 0, items = this.items, c; c = items[i]; i++) {
            if (this.valueForNode(c) == value) {
                return i
            }
        }
        return value
    },
    valueForNode: function (node) {
        return node[this.valueattr] || node.getAttribute(this.valueattr)
    },
    selectionSelect: function (e, detail) {
        this.updateSelectedItem();
        if (detail.item) {
            this.applySelection(detail.item, detail.isSelected)
        }
    },
    applySelection: function (item, isSelected) {
        if (this.selectedClass) {
            item.classList.toggle(this.selectedClass, isSelected)
        }
        if (this.selectedProperty) {
            item[this.selectedProperty] = isSelected
        }
        if (this.selectedAttribute && item.setAttribute) {
            if (isSelected) {
                item.setAttribute(this.selectedAttribute, "")
            } else {
                item.removeAttribute(this.selectedAttribute)
            }
        }
    },
    activateHandler: function (e) {
        if (!this.notap) {
            var i = this.findDistributedTarget(e.target, this.items);
            if (i >= 0) {
                var item = this.items[i];
                var s = this.valueForNode(item) || i;
                if (this.multi) {
                    if (this.selected) {
                        this.addRemoveSelected(s)
                    } else {
                        this.selected = [s]
                    }
                } else {
                    this.selected = s
                }
                this.asyncFire("core-activate", {item: item})
            }
        }
    },
    addRemoveSelected: function (value) {
        var i = this.selected.indexOf(value);
        if (i >= 0) {
            this.selected.splice(i, 1)
        } else {
            this.selected.push(value)
        }
    },
    findDistributedTarget: function (target, nodes) {
        while (target && target != this) {
            var i = Array.prototype.indexOf.call(nodes, target);
            if (i >= 0) {
                return i
            }
            target = target.parentNode
        }
    },
    selectIndex: function (index) {
        var item = this.items[index];
        if (item) {
            this.selected = this.valueForNode(item) || index;
            return item
        }
    },
    selectPrevious: function (wrapped) {
        var i = wrapped && !this.selectedIndex ? this.items.length - 1 : this.selectedIndex - 1;
        return this.selectIndex(i)
    },
    selectNext: function (wrapped) {
        var i = wrapped && this.selectedIndex >= this.items.length - 1 ? 0 : this.selectedIndex + 1;
        return this.selectIndex(i)
    }
});
Polymer("core-menu");
(function () {
    var SKIP_ID = "meta";
    var metaData = {}, metaArray = {};
    Polymer("core-meta", {
        type: "default", alwaysPrepare: true, ready: function () {
            this.register(this.id)
        }, get metaArray() {
            var t = this.type;
            if (!metaArray[t]) {
                metaArray[t] = []
            }
            return metaArray[t]
        }, get metaData() {
            var t = this.type;
            if (!metaData[t]) {
                metaData[t] = {}
            }
            return metaData[t]
        }, register: function (id, old) {
            if (id && id !== SKIP_ID) {
                this.unregister(this, old);
                this.metaData[id] = this;
                this.metaArray.push(this)
            }
        }, unregister: function (meta, id) {
            delete this.metaData[id || meta.id];
            var i = this.metaArray.indexOf(meta);
            if (i >= 0) {
                this.metaArray.splice(i, 1)
            }
        }, get list() {
            return this.metaArray
        }, byId: function (id) {
            return this.metaData[id]
        }
    })
})();
Polymer("core-iconset", {
    src: "",
    width: 0,
    icons: "",
    iconSize: 24,
    offsetX: 0,
    offsetY: 0,
    type: "iconset",
    created: function () {
        this.iconMap = {};
        this.iconNames = [];
        this.themes = {}
    },
    ready: function () {
        if (this.src && this.ownerDocument !== document) {
            this.src = this.resolvePath(this.src, this.ownerDocument.baseURI)
        }
        this.super();
        this.updateThemes()
    },
    iconsChanged: function () {
        var ox = this.offsetX;
        var oy = this.offsetY;
        this.icons && this.icons.split(/\s+/g).forEach(function (name, i) {
            this.iconNames.push(name);
            this.iconMap[name] = {offsetX: ox, offsetY: oy};
            if (ox + this.iconSize < this.width) {
                ox += this.iconSize
            } else {
                ox = this.offsetX;
                oy += this.iconSize
            }
        }, this)
    },
    updateThemes: function () {
        var ts = this.querySelectorAll("property[theme]");
        ts && ts.array().forEach(function (t) {
            this.themes[t.getAttribute("theme")] = {
                offsetX: parseInt(t.getAttribute("offsetX")) || 0,
                offsetY: parseInt(t.getAttribute("offsetY")) || 0
            }
        }, this)
    },
    getOffset: function (icon, theme) {
        var i = this.iconMap[icon];
        if (!i) {
            var n = this.iconNames[Number(icon)];
            i = this.iconMap[n]
        }
        var t = this.themes[theme];
        if (i && t) {
            return {offsetX: i.offsetX + t.offsetX, offsetY: i.offsetY + t.offsetY}
        }
        return i
    },
    applyIcon: function (element, icon, scale) {
        var offset = this.getOffset(icon);
        scale = scale || 1;
        if (element && offset) {
            var icon = element._icon || document.createElement("div");
            var style = icon.style;
            style.backgroundImage = "url(" + this.src + ")";
            style.backgroundPosition = -offset.offsetX * scale + "px" + " " + (-offset.offsetY * scale + "px");
            style.backgroundSize = scale === 1 ? "auto" : this.width * scale + "px";
            if (icon.parentNode !== element) {
                element.appendChild(icon)
            }
            return icon
        }
    }
});
(function () {
    var meta;
    Polymer("core-icon", {
        src: "",
        icon: "",
        alt: null,
        observe: {icon: "updateIcon", alt: "updateAlt"},
        defaultIconset: "icons",
        ready: function () {
            if (!meta) {
                meta = document.createElement("core-iconset")
            }
            if (this.hasAttribute("aria-label")) {
                if (!this.hasAttribute("role")) {
                    this.setAttribute("role", "img")
                }
                return
            }
            this.updateAlt()
        },
        srcChanged: function () {
            var icon = this._icon || document.createElement("div");
            icon.textContent = "";
            icon.setAttribute("fit", "");
            icon.style.backgroundImage = "url(" + this.src + ")";
            icon.style.backgroundPosition = "center";
            icon.style.backgroundSize = "100%";
            if (!icon.parentNode) {
                this.appendChild(icon)
            }
            this._icon = icon
        },
        getIconset: function (name) {
            return meta.byId(name || this.defaultIconset)
        },
        updateIcon: function (oldVal, newVal) {
            if (!this.icon) {
                this.updateAlt();
                return
            }
            var parts = String(this.icon).split(":");
            var icon = parts.pop();
            if (icon) {
                var set = this.getIconset(parts.pop());
                if (set) {
                    this._icon = set.applyIcon(this, icon);
                    if (this._icon) {
                        this._icon.setAttribute("fit", "")
                    }
                }
            }
            if (oldVal) {
                if (oldVal.split(":").pop() == this.getAttribute("aria-label")) {
                    this.updateAlt()
                }
            }
        },
        updateAlt: function () {
            if (this.getAttribute("aria-hidden")) {
                return
            }
            if (this.alt === "") {
                this.setAttribute("aria-hidden", "true");
                if (this.hasAttribute("role")) {
                    this.removeAttribute("role")
                }
                if (this.hasAttribute("aria-label")) {
                    this.removeAttribute("aria-label")
                }
            } else {
                this.setAttribute("aria-label", this.alt || this.icon.split(":").pop());
                if (!this.hasAttribute("role")) {
                    this.setAttribute("role", "img")
                }
                if (this.hasAttribute("aria-hidden")) {
                    this.removeAttribute("aria-hidden")
                }
            }
        }
    })
})();
Polymer("core-item", {});
Polymer("core-header-panel", {
    publish: {mode: {value: "", reflect: true}, tallClass: "tall", shadow: false},
    animateDuration: 200,
    modeConfigs: {
        shadowMode: {waterfall: 1, "waterfall-tall": 1},
        noShadow: {seamed: 1, cover: 1, scroll: 1},
        tallMode: {"waterfall-tall": 1},
        outerScroll: {scroll: 1}
    },
    ready: function () {
        this.scrollHandler = this.scroll.bind(this);
        this.addListener()
    },
    detached: function () {
        this.removeListener(this.mode)
    },
    addListener: function () {
        this.scroller.addEventListener("scroll", this.scrollHandler)
    },
    removeListener: function (mode) {
        var s = this.getScrollerForMode(mode);
        s.removeEventListener("scroll", this.scrollHandler)
    },
    domReady: function () {
        this.async("scroll")
    },
    modeChanged: function (old) {
        var configs = this.modeConfigs;
        var header = this.header;
        if (header) {
            if (configs.tallMode[old] && !configs.tallMode[this.mode]) {
                header.classList.remove(this.tallClass);
                this.async(function () {
                    header.classList.remove("animate")
                }, null, this.animateDuration)
            } else {
                header.classList.toggle("animate", configs.tallMode[this.mode])
            }
        }
        if (configs && (configs.outerScroll[this.mode] || configs.outerScroll[old])) {
            this.removeListener(old);
            this.addListener()
        }
        this.scroll()
    },
    get header() {
        return this.$.headerContent.getDistributedNodes()[0]
    },
    getScrollerForMode: function (mode) {
        return this.modeConfigs.outerScroll[mode] ? this.$.outerContainer : this.$.mainContainer
    },
    get scroller() {
        return this.getScrollerForMode(this.mode)
    },
    scroll: function () {
        var configs = this.modeConfigs;
        var main = this.$.mainContainer;
        var header = this.header;
        var sTop = main.scrollTop;
        var atTop = sTop === 0;
        this.$.dropShadow.classList.toggle("hidden", !this.shadow && (atTop && configs.shadowMode[this.mode] || configs.noShadow[this.mode]));
        if (header && configs.tallMode[this.mode]) {
            header.classList.toggle(this.tallClass, atTop || header.classList.contains(this.tallClass) && main.scrollHeight < this.$.outerContainer.offsetHeight)
        }
        this.fire("scroll", {target: this.scroller}, this, false)
    }
});
Polymer("core-media-query", {
    queryMatches: false, query: "", ready: function () {
        this._mqHandler = this.queryHandler.bind(this);
        this._mq = null
    }, queryChanged: function () {
        if (this._mq) {
            this._mq.removeListener(this._mqHandler)
        }
        var query = this.query;
        if (query[0] !== "(") {
            query = "(" + this.query + ")"
        }
        this._mq = window.matchMedia(query);
        this._mq.addListener(this._mqHandler);
        this.queryHandler(this._mq)
    }, queryHandler: function (mq) {
        this.queryMatches = mq.matches;
        this.asyncFire("core-media-change", mq)
    }
});
Polymer("core-drawer-panel", {
    publish: {
        drawerWidth: "256px",
        responsiveWidth: "640px",
        selected: {value: null, reflect: true},
        defaultSelected: "main",
        narrow: {value: false, reflect: true},
        rightDrawer: false,
        disableSwipe: false,
        forceNarrow: false
    },
    eventDelegates: {
        trackstart: "trackStart",
        trackx: "trackx",
        trackend: "trackEnd",
        down: "downHandler",
        up: "upHandler",
        tap: "tapHandler"
    },
    transition: false,
    edgeSwipeSensitivity: 15,
    peeking: false,
    dragging: false,
    hasTransform: true,
    hasWillChange: true,
    toggleAttribute: "core-drawer-toggle",
    created: function () {
        this.hasTransform = "transform"in this.style;
        this.hasWillChange = "willChange"in this.style
    },
    domReady: function () {
        this.async(function () {
            this.transition = true
        })
    },
    togglePanel: function () {
        this.selected = this.isMainSelected() ? "drawer" : "main"
    },
    openDrawer: function () {
        this.selected = "drawer"
    },
    closeDrawer: function () {
        this.selected = "main"
    },
    queryMatchesChanged: function () {
        this.narrow = this.queryMatches || this.forceNarrow;
        if (this.narrow) {
            this.selected = this.defaultSelected
        }
        this.setAttribute("touch-action", this.swipeAllowed() ? "pan-y" : "");
        this.fire("core-responsive-change", {narrow: this.narrow})
    },
    forceNarrowChanged: function () {
        this.queryMatchesChanged()
    },
    swipeAllowed: function () {
        return this.narrow && !this.disableSwipe
    },
    isMainSelected: function () {
        return this.selected === "main"
    },
    startEdgePeek: function () {
        this.width = this.$.drawer.offsetWidth;
        this.moveDrawer(this.translateXForDeltaX(this.rightDrawer ? -this.edgeSwipeSensitivity : this.edgeSwipeSensitivity));
        this.peeking = true
    },
    stopEdgePeak: function () {
        if (this.peeking) {
            this.peeking = false;
            this.moveDrawer(null)
        }
    },
    downHandler: function (e) {
        if (!this.dragging && this.isMainSelected() && this.isEdgeTouch(e)) {
            this.startEdgePeek()
        }
    },
    upHandler: function (e) {
        this.stopEdgePeak()
    },
    tapHandler: function (e) {
        if (e.target && this.toggleAttribute && e.target.hasAttribute(this.toggleAttribute)) {
            this.togglePanel()
        }
    },
    isEdgeTouch: function (e) {
        return this.swipeAllowed() && (this.rightDrawer ? e.pageX >= this.offsetWidth - this.edgeSwipeSensitivity : e.pageX <= this.edgeSwipeSensitivity)
    },
    trackStart: function (e) {
        if (this.swipeAllowed()) {
            this.dragging = true;
            if (this.isMainSelected()) {
                this.dragging = this.peeking || this.isEdgeTouch(e)
            }
            if (this.dragging) {
                this.width = this.$.drawer.offsetWidth;
                this.transition = false;
                e.preventTap()
            }
        }
    },
    translateXForDeltaX: function (deltaX) {
        var isMain = this.isMainSelected();
        if (this.rightDrawer) {
            return Math.max(0, isMain ? this.width + deltaX : deltaX)
        } else {
            return Math.min(0, isMain ? deltaX - this.width : deltaX)
        }
    },
    trackx: function (e) {
        if (this.dragging) {
            if (this.peeking) {
                if (Math.abs(e.dx) <= this.edgeSwipeSensitivity) {
                    return
                }
                this.peeking = false
            }
            this.moveDrawer(this.translateXForDeltaX(e.dx))
        }
    },
    trackEnd: function (e) {
        if (this.dragging) {
            this.dragging = false;
            this.transition = true;
            this.moveDrawer(null);
            if (this.rightDrawer) {
                this.selected = e.xDirection > 0 ? "main" : "drawer"
            } else {
                this.selected = e.xDirection > 0 ? "drawer" : "main"
            }
        }
    },
    transformForTranslateX: function (translateX) {
        if (translateX === null) {
            return ""
        }
        return this.hasWillChange ? "translateX(" + translateX + "px)" : "translate3d(" + translateX + "px, 0, 0)"
    },
    moveDrawer: function (translateX) {
        var s = this.$.drawer.style;
        if (this.hasTransform) {
            s.transform = this.transformForTranslateX(translateX)
        } else {
            s.webkitTransform = this.transformForTranslateX(translateX)
        }
    }
});
Polymer("core-iconset-svg", {
    iconSize: 24, type: "iconset", created: function () {
        this._icons = {}
    }, ready: function () {
        this.super();
        this.updateIcons()
    }, iconById: function (id) {
        return this._icons[id] || (this._icons[id] = this.querySelector('[id="' + id + '"]'))
    }, cloneIcon: function (id) {
        var icon = this.iconById(id);
        if (icon) {
            var content = icon.cloneNode(true);
            content.removeAttribute("id");
            var svg = document.createElementNS("http://www.w3.org/2000/svg", "svg");
            svg.setAttribute("viewBox", "0 0 " + this.iconSize + " " + this.iconSize);
            svg.style.pointerEvents = "none";
            svg.appendChild(content);
            return svg
        }
    }, get iconNames() {
        if (!this._iconNames) {
            this._iconNames = this.findIconNames()
        }
        return this._iconNames
    }, findIconNames: function () {
        var icons = this.querySelectorAll("[id]").array();
        if (icons.length) {
            return icons.map(function (n) {
                return n.id
            })
        }
    }, applyIcon: function (element, icon) {
        var root = element;
        var old = root.querySelector("svg");
        if (old) {
            old.remove()
        }
        var svg = this.cloneIcon(icon);
        if (!svg) {
            return
        }
        svg.setAttribute("height", "100%");
        svg.setAttribute("width", "100%");
        svg.setAttribute("preserveAspectRatio", "xMidYMid meet");
        svg.style.display = "block";
        root.insertBefore(svg, root.firstElementChild);
        return svg
    }, updateIcons: function (selector, method) {
        selector = selector || "[icon]";
        method = method || "updateIcon";
        var deep = window.ShadowDOMPolyfill ? "" : "html /deep/ ";
        var i$ = document.querySelectorAll(deep + selector);
        for (var i = 0, e; e = i$[i]; i++) {
            if (e[method]) {
                e[method].call(e)
            }
        }
    }
});
Polymer("core-icon-button", {
    src: "", active: false, icon: "", activeChanged: function () {
        this.classList.toggle("selected", this.active)
    }
});
Polymer("core-scaffold", {
    publish: {
        drawerWidth: "256px",
        responsiveWidth: "600px",
        rightDrawer: false,
        disableSwipe: false,
        mode: {value: "seamed", reflect: true}
    }, ready: function () {
        this._scrollHandler = this.scroll.bind(this);
        this.$.headerPanel.addEventListener("scroll", this._scrollHandler)
    }, detached: function () {
        this.$.headerPanel.removeEventListener("scroll", this._scrollHandler)
    }, togglePanel: function () {
        this.$.drawerPanel.togglePanel()
    }, openDrawer: function () {
        this.$.drawerPanel.openDrawer()
    }, closeDrawer: function () {
        this.$.drawerPanel.closeDrawer()
    }, scroll: function (e) {
        this.fire("scroll", {target: e.detail.target}, this, false)
    }
});
